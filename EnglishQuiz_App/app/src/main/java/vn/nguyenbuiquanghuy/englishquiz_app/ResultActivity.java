package vn.nguyenbuiquanghuy.englishquiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ResultActivity extends AppCompatActivity {
    ListView lvResults;
    ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Ánh xạ ListView
        lvResults = findViewById(R.id.lv_results);

        // Lấy dữ liệu từ Intent
        ArrayList<String> questions = getIntent().getStringArrayListExtra("questions");
        ArrayList<String> correctAnswers = getIntent().getStringArrayListExtra("correctAnswers");
        ArrayList<String> selectedAnswers = getIntent().getStringArrayListExtra("selectedAnswers");

        // Khởi tạo adapter và gán vào ListView
        resultAdapter = new ResultAdapter(ResultActivity.this, questions, correctAnswers, selectedAnswers);
        lvResults.setAdapter(resultAdapter);
    }
}