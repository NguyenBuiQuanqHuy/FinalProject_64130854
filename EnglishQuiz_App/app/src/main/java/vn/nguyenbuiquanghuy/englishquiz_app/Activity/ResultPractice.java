package vn.nguyenbuiquanghuy.englishquiz_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import vn.nguyenbuiquanghuy.englishquiz_app.Adapter.ResultAdapter;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class ResultPractice extends AppCompatActivity {
    ListView lvResults;
    ResultAdapter resultAdapter;
    Button btnRetry,btnExit;
    TextView tv_score, tv_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_practice);

        lvResults = findViewById(R.id.lv_results);
        btnRetry=findViewById(R.id.btn_retry);
        btnExit=findViewById(R.id.btn_exit);
        tv_score=findViewById(R.id.tv_score);
        tv_total=findViewById(R.id.tv_total);

        ArrayList<String> questions = getIntent().getStringArrayListExtra("questions");
        ArrayList<String> correctAnswers = getIntent().getStringArrayListExtra("correctAnswers");
        ArrayList<String> selectedAnswers = getIntent().getStringArrayListExtra("selectedAnswers");
        String topic = getIntent().getStringExtra("topic");
        resultAdapter = new ResultAdapter(ResultPractice.this, questions, correctAnswers, selectedAnswers);
        lvResults.setAdapter(resultAdapter);

        int correctCount = 0;
        for (int i = 0; i < selectedAnswers.size(); i++) {
            if (selectedAnswers.get(i).equals(correctAnswers.get(i))) {
                correctCount++;
            }
        }
        tv_score.setText(String.valueOf(correctCount));
        tv_total.setText("/" + questions.size());
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ResultPractice.this, PracticeActivity.class);
                intent.putExtra("topic", topic); // Gửi lại chủ đề đã chọn
                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultPractice.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}