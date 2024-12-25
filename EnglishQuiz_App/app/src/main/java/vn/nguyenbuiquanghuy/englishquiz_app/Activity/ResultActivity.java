package vn.nguyenbuiquanghuy.englishquiz_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.nguyenbuiquanghuy.englishquiz_app.Adapter.ResultAdapter;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class ResultActivity extends AppCompatActivity {
    ListView lvResults;
    ResultAdapter resultAdapter;
    Button btnRetry,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Ánh xạ ListView
        lvResults = findViewById(R.id.lv_results);
        btnRetry=findViewById(R.id.btn_retry);
        btnExit=findViewById(R.id.btn_exit);

        // Lấy dữ liệu từ Intent
        ArrayList<String> questions = getIntent().getStringArrayListExtra("questions");
        ArrayList<String> correctAnswers = getIntent().getStringArrayListExtra("correctAnswers");
        ArrayList<String> selectedAnswers = getIntent().getStringArrayListExtra("selectedAnswers");
        String topic = getIntent().getStringExtra("topic");
        // Khởi tạo adapter và gán vào ListView
        resultAdapter = new ResultAdapter(ResultActivity.this, questions, correctAnswers, selectedAnswers);
        lvResults.setAdapter(resultAdapter);

        // Xử lý nút Retry để làm lại
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ResultActivity.this, QuizActivity.class);
                intent.putExtra("topic", topic); // Gửi lại chủ đề đã chọn
                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
