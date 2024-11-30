package vn.nguyenbuiquanghuy.android_project.Results;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.nguyenbuiquanghuy.android_project.Quiz.Questions;
import vn.nguyenbuiquanghuy.android_project.R;

public class ResultsActivity extends AppCompatActivity {
    TextView tvResults;
    LinearLayout layoutResults; // Layout chứa danh sách các câu hỏi và đáp án

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Ánh xạ view
        tvResults = findViewById(R.id.tv_results);
        layoutResults = findViewById(R.id.layout_results);

        // Nhận dữ liệu từ Quiz
        ArrayList<Questions> questionList = (ArrayList<Questions>) getIntent().getSerializableExtra("questionList");
        ArrayList<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");

        if (questionList != null && userAnswers != null) {
            int correctCount = 0;
            for (int i = 0; i < questionList.size(); i++) {
                Questions question = questionList.get(i);
                String userAnswer = userAnswers.get(i);
                String correctAnswer = question.getAnswer();

                // Tạo view hiển thị câu hỏi và đáp án
                TextView tvQuestion = new TextView(this);
                tvQuestion.setText("Câu " + (i + 1) + ": " + question.getQuestion());
                tvQuestion.setTextSize(18);

                // Kiểm tra câu trả lời đúng hay sai
                TextView tvAnswer = new TextView(this);
                if (userAnswer.equals(correctAnswer)) {
                    tvAnswer.setText("Đáp án đúng: " + correctAnswer);
                    tvAnswer.setBackgroundColor(Color.GREEN); // Nền xanh cho câu đúng
                    correctCount++;
                } else {
                    tvAnswer.setText("Đáp án sai. Đáp án đúng là: " + correctAnswer);
                    tvAnswer.setBackgroundColor(Color.RED); // Nền đỏ cho câu sai
                }
                tvAnswer.setPadding(16, 16, 16, 16);

                // Thêm câu hỏi và đáp án vào layout
                layoutResults.addView(tvQuestion);
                layoutResults.addView(tvAnswer);
            }

            // Hiển thị số câu đúng
            tvResults.setText("Số câu đúng: " + correctCount + " / " + questionList.size());
        }
    }
}