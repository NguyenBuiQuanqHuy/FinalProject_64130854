package vn.nguyenbuiquanghuy.englishquiz_app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.nguyenbuiquanghuy.englishquiz_app.Database.HistoryDatabase;
import vn.nguyenbuiquanghuy.englishquiz_app.Model.Questions;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class QuizActivity extends AppCompatActivity {
    TextView tvTopic,tvQuestion,tvTimer;
    RadioGroup rgOptions;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    Button btnNext,btnExit;
    List<Questions> questionList;
    int currentQuestionIndex = 0;
    List<String> questions = new ArrayList<>();
    List<String> correctAnswers = new ArrayList<>();
    List<String> selectedAnswers = new ArrayList<>();
    CountDownTimer countDownTimer;
    int TIMER_DURATION;
    int NUMBER_OF_QUESTIONS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        String topic = getIntent().getStringExtra("topic");
        if (topic == null) {
            Toast.makeText(this, "Không tìm thấy chủ đề!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadSettings();
        tvTopic=findViewById(R.id.tv_TopicQuiz);
        tvQuestion = findViewById(R.id.tv_results);
        tvTimer = findViewById(R.id.tv_timer);
        rgOptions = findViewById(R.id.rg_results);
        rbOption1 = findViewById(R.id.rb_result1);
        rbOption2 = findViewById(R.id.rb_result2);
        rbOption3 = findViewById(R.id.rb_result3);
        rbOption4 = findViewById(R.id.rb_result4);
        btnNext = findViewById(R.id.btn_next);
        btnExit =findViewById(R.id.btn_exit);

        loadQuestionsFromJson(topic);

        tvTopic.setText(topic);
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
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                Intent intent=new Intent(QuizActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestionsFromJson(String topic) {
        try {
            InputStream inputStream = getAssets().open("englishquiz-app.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONObject questions = jsonObject.getJSONObject("questions");
            JSONObject topicQuestions = questions.getJSONObject(topic);

            questionList = new ArrayList<>();

            for (int i = 1; i <= topicQuestions.length(); i++) {
                String questionKey = "question" + i;
                if (topicQuestions.has(questionKey)) {
                    JSONObject questionJson = topicQuestions.getJSONObject(questionKey);
                    Questions question = new Questions();
                    question.setQuestion(questionJson.getString("question"));
                    question.setOption1(questionJson.getString("option1"));
                    question.setOption2(questionJson.getString("option2"));
                    question.setOption3(questionJson.getString("option3"));
                    question.setOption4(questionJson.getString("option4"));
                    question.setAnswer(questionJson.getString("answer"));
                    questionList.add(question);
                }
            }

            if (!questionList.isEmpty()) {
                List<Questions> randomQuestions = getRandomQuestions(NUMBER_OF_QUESTIONS);
                questionList = randomQuestions;
                displayQuestion(currentQuestionIndex);
            } else {
                Toast.makeText(this, "Không có câu hỏi nào trong chủ đề này.", Toast.LENGTH_SHORT).show();
                finish();
            }

        } catch (IOException | JSONException e) {
            Toast.makeText(this, "Lỗi đọc dữ liệu từ JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayQuestion(int index) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (index < questionList.size()) {
            Questions question = questionList.get(index);
            tvQuestion.setText(question.getQuestion());
            rbOption1.setText(question.getOption1());
            rbOption2.setText(question.getOption2());
            rbOption3.setText(question.getOption3());
            rbOption4.setText(question.getOption4());
            rgOptions.clearCheck();
        }
        startCountDownTimer();
    }

    private void loadSettings() {
        SharedPreferences preferences = getSharedPreferences("QuizSettings", MODE_PRIVATE);
        int timeLimit = preferences.getInt("timeLimit", 10);
        int numberOfQuestions = preferences.getInt("numberOfQuestions", 10);

        TIMER_DURATION = timeLimit * 1000;
        NUMBER_OF_QUESTIONS = numberOfQuestions;
    }

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time left: " + millisUntilFinished/ 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("Time's up!");
                   checkAnswer();
            }
        };
        countDownTimer.start();
    }

    // Phương thức để lấy 5 câu hỏi ngẫu nhiên
    private List<Questions> getRandomQuestions(int count) {
        List<Questions> randomQuestions = new ArrayList<>();
        List<Questions> shuffledList = new ArrayList<>(questionList);
        java.util.Collections.shuffle(shuffledList);
        for (int i = 0; i < count; i++) {
            randomQuestions.add(shuffledList.get(i));
        }
        return randomQuestions;
    }

    private void checkAnswer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Hủy bộ đếm thời gian
        }
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
            int score = 0;
            for (int i = 0; i < correctAnswers.size(); i++) {
                if (correctAnswers.get(i).equals(selectedAnswers.get(i))) {
                    score++;
                }
            }

            HistoryDatabase dbHelper = new HistoryDatabase(this);
            String topic = tvTopic.getText().toString();
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()); // Lấy thời gian hiện tại

            long resultId = dbHelper.insertQuizResult(topic, score, correctAnswers.size(), dateTime);
            if (resultId != -1) {
                Toast.makeText(this, "Result is saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Result is not saved", Toast.LENGTH_SHORT).show();
            }

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
