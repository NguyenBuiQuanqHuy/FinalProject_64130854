package vn.nguyenbuiquanghuy.android_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

    private FirebaseDatabase database;
    private DatabaseReference questionRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);

        // Khởi tạo các thành phần UI
        tvQuestion = findViewById(R.id.tv_question);
        rgOptions = findViewById(R.id.rg_options);
        rbOption1 = findViewById(R.id.rb_option1);
        rbOption2 = findViewById(R.id.rb_option2);
        rbOption3 = findViewById(R.id.rb_option3);
        rbOption4 = findViewById(R.id.rb_option4);
        btnNext = findViewById(R.id.btn_next);

        database = FirebaseDatabase.getInstance();
        questionRef = database.getReference("questions");
        // Load câu hỏi từ Firebase
        loadQuestionsFromFirebase();
        // Xử lý sự kiện cho nút Next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rgOptions.getCheckedRadioButtonId() != -1) {
                    int selectedOption = rgOptions.getCheckedRadioButtonId();
                    checkAnswer(selectedOption);
                } else {
                    Toast.makeText(Quiz.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadQuestionsFromFirebase() {
        questionList = new ArrayList<Questions>();
        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Questions question = snapshot.getValue(Questions.class);
                    questionList.add(question);
                }
                displayQuestion(currentQuestionIndex);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Quiz.this, "Failed to load questions", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayQuestion(int index) {
        if (index < questionList.size()) {
            Questions question = questionList.get(index);

            // Hiển thị câu hỏi
            tvQuestion.setText(question.getQuestionText());

            // Hiển thị các lựa chọn
            rbOption1.setText(question.getOptions().get(0));
            rbOption2.setText(question.getOptions().get(1));
            rbOption3.setText(question.getOptions().get(2));
            rbOption4.setText(question.getOptions().get(3));
        }
    }

    private void checkAnswer(int selectedOption) {
        Questions currentQuestion = questionList.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getCorrectAnswer();

        String selectedAnswer = "";
        if (selectedOption == rbOption1.getId()) {
            selectedAnswer = rbOption1.getText().toString();
        } else if (selectedOption == rbOption2.getId()) {
            selectedAnswer = rbOption2.getText().toString();
        } else if (selectedOption == rbOption3.getId()) {
            selectedAnswer = rbOption3.getText().toString();
        } else if (selectedOption == rbOption4.getId()) {
            selectedAnswer = rbOption4.getText().toString();
        }

        if (selectedAnswer.equals(correctAnswer)) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect. The correct answer is: " + correctAnswer, Toast.LENGTH_LONG).show();
        }

        // Tăng chỉ số câu hỏi và hiển thị câu hỏi tiếp theo
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
            rgOptions.clearCheck();  // Dọn dẹp lựa chọn trước
        } else {
            Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_SHORT).show();
        }
    }
}