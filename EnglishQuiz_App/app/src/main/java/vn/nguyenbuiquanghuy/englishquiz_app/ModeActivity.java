package vn.nguyenbuiquanghuy.englishquiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.nguyenbuiquanghuy.englishquiz_app.Activity.QuizActivity;

public class ModeActivity extends AppCompatActivity {
    Button btnExam, btnPractice;
    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mode);

        topic=getIntent().getStringExtra("topic");
        btnExam=findViewById(R.id.btn_exam_mode);
        btnPractice=findViewById(R.id.btn_practice_mode);

        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModeActivity.this, QuizActivity.class);
                intent.putExtra("topic", topic);
                startActivity(intent);
                finish();
            }
        });

        btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModeActivity.this, PracticeActivity.class);
                intent.putExtra("topic", topic);
                startActivity(intent);
                finish();
            }
        });
    }
}