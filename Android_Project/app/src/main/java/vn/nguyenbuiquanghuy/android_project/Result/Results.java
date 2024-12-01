package vn.nguyenbuiquanghuy.android_project.Result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import vn.nguyenbuiquanghuy.android_project.R;

public class Results extends AppCompatActivity {
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
        resultAdapter = new ResultAdapter(Results.this, questions, correctAnswers, selectedAnswers);
        lvResults.setAdapter(resultAdapter);
    }
}
