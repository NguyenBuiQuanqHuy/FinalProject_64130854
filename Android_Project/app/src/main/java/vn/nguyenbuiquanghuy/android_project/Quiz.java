package vn.nguyenbuiquanghuy.android_project;

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

public class Quiz extends AppCompatActivity {
    private TextView tvQuestion;
    private RadioGroup rgOptions;
    private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    private Button btnNext;

    private List<Questions> questionList;
    private int currentQuestionIndex = 0;

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
        tvQuestion = findViewById(R.id.tv_question);
        rgOptions = findViewById(R.id.rg_options);
        rbOption1 = findViewById(R.id.rb_option1);
        rbOption2 = findViewById(R.id.rb_option2);
        rbOption3 = findViewById(R.id.rb_option3);
        rbOption4 = findViewById(R.id.rb_option4);
        btnNext = findViewById(R.id.btn_next);

        // Kết nối Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        questionRef = database.getReference("questions").child(topic);

        // Tải dữ liệu từ Firebase
        loadQuestionsFromFirebase();

        // Xử lý nút Next
        btnNext.setOnClickListener(v -> {
            if (rgOptions.getCheckedRadioButtonId() != -1) {
                checkAnswer();
            } else {
                Toast.makeText(Quiz.this, "Vui lòng chọn một đáp án.", Toast.LENGTH_SHORT).show();
            }
        });
    }

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

                    // Kiểm tra nếu có câu hỏi để hiển thị
                    if (!questionList.isEmpty()) {
                        displayQuestion(currentQuestionIndex);
                    } else {
                        Toast.makeText(Quiz.this, "Không có câu hỏi nào cho chủ đề này.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(Quiz.this, "Không có dữ liệu trong Firebase.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Quiz.this, "Lỗi tải dữ liệu: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sai rồi. Đáp án đúng là: " + correctAnswer, Toast.LENGTH_LONG).show();
        }

        // Hiển thị câu hỏi tiếp theo hoặc kết thúc
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        } else {
            Toast.makeText(this, "Bạn đã hoàn thành bài kiểm tra!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
