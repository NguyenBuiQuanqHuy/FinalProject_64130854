package vn.nguyenbuiquanghuy.android_project.Result;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.nguyenbuiquanghuy.android_project.R;

public class ResultActivity extends AppCompatActivity {
    ListView lvResults;
    ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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
