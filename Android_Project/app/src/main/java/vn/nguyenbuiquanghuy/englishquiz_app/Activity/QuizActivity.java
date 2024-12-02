package vn.nguyenbuiquanghuy.englishquiz_app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.nguyenbuiquanghuy.englishquiz_app.Model.Questions;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class QuizActivity extends AppCompatActivity {
    TextView tvTopic,tvQuestion;
    RadioGroup rgOptions;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnNext,btnExit;

    List<Questions> questionList;
    int currentQuestionIndex = 0;
    List<String> questions = new ArrayList<>();
    List<String> correctAnswers = new ArrayList<>();
    List<String> selectedAnswers = new ArrayList<>();

    private DatabaseReference questionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Lấy chủ đề từ Intent
        String topic = getIntent().getStringExtra("topic");
        if (topic == null) {
            Toast.makeText(this, "Không tìm thấy chủ đề!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Ánh xạ view
        tvTopic=findViewById(R.id.tv_TopicQuiz);
        tvQuestion = findViewById(R.id.tv_results);
        rgOptions = findViewById(R.id.rg_results);
        rbOption1 = findViewById(R.id.rb_result1);
        rbOption2 = findViewById(R.id.rb_result2);
        rbOption3 = findViewById(R.id.rb_result3);
        rbOption4 = findViewById(R.id.rb_result4);
        btnNext = findViewById(R.id.btn_next);
        btnExit =findViewById(R.id.btn_exit);

        // Kết nối Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        questionRef = database.getReference("questions").child(topic);

        // Tải dữ liệu từ Firebase
        loadQuestionsFromFirebase();

        tvTopic.setText(topic);
        // Xử lý nút Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rgOptions.getCheckedRadioButtonId() != -1) {
                    checkAnswer();
                } else {
                    Toast.makeText(QuizActivity.this, "Vui lòng chọn một đáp án.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý nút Exit để quay về màn hình chính
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Hàm lấy câu hỏi trắc nghiệm từ Firebase
    private void loadQuestionsFromFirebase() {
        questionList = new ArrayList<>();
        questionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Questions question = snapshot.getValue(Questions.class);
                        if (question != null) {
                            questionList.add(question);
                        }
                    }

                    // Lọc 5 câu hỏi ngẫu nhiên nếu có ít nhất 5 câu hỏi
                    if (questionList.size() > 1) {
                        List<Questions> randomQuestions = getRandomQuestions(5);
                        questionList.clear();
                        questionList.addAll(randomQuestions);
                    }

                    // Kiểm tra nếu có câu hỏi để hiển thị
                    if (!questionList.isEmpty()) {
                        displayQuestion(currentQuestionIndex);
                    } else {
                        Toast.makeText(QuizActivity.this, "Không có câu hỏi nào cho chủ đề này.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(QuizActivity.this, "Không có dữ liệu trong Firebase.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(QuizActivity.this, "Lỗi tải dữ liệu: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void displayQuestion(int index) {
        if (index < questionList.size()) {
            Questions question = questionList.get(index);
            tvQuestion.setText(question.getQuestion());

            rbOption1.setText(question.getOption1());
            rbOption2.setText(question.getOption2());
            rbOption3.setText(question.getOption3());
            rbOption4.setText(question.getOption4());

            rgOptions.clearCheck(); // Xóa lựa chọn cũ
        }
    }

    // Phương thức để lấy 5 câu hỏi ngẫu nhiên
    private List<Questions> getRandomQuestions(int count) {
        List<Questions> randomQuestions = new ArrayList<>();
        List<Questions> shuffledList = new ArrayList<>(questionList);
        java.util.Collections.shuffle(shuffledList); // Xáo trộn danh sách câu hỏi

        for (int i = 0; i < count; i++) {
            randomQuestions.add(shuffledList.get(i)); // Lấy 5 câu hỏi đầu tiên từ danh sách đã xáo trộn
        }

        return randomQuestions;
    }

    private void checkAnswer() {
       Questions currentQuestion = questionList.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getAnswer();


        String selectedAnswer = "";
        int selectedOptionId = rgOptions.getCheckedRadioButtonId();

        if (selectedOptionId == rbOption1.getId()) {
          selectedAnswer = rbOption1.getText().toString();
       } else if (selectedOptionId == rbOption2.getId()) {
            selectedAnswer = rbOption2.getText().toString();
       } else if (selectedOptionId == rbOption3.getId()) {
           selectedAnswer = rbOption3.getText().toString();
      } else if (selectedOptionId == rbOption4.getId()) {
            selectedAnswer = rbOption4.getText().toString();
       }
        // Lưu câu hỏi, đáp án đúng, và đáp án đã chọn
        questions.add(currentQuestion.getQuestion());
        correctAnswers.add(correctAnswer);
        selectedAnswers.add(selectedAnswer);

        if (currentQuestionIndex < questionList.size()-1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            // Chuyển sang màn hình kết quả
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putStringArrayListExtra("questions", (ArrayList<String>) questions);
            intent.putStringArrayListExtra("correctAnswers", (ArrayList<String>) correctAnswers);
            intent.putStringArrayListExtra("selectedAnswers", (ArrayList<String>) selectedAnswers);
            intent.putExtra("topic", tvTopic.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}