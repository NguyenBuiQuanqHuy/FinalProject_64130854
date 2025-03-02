package vn.nguyenbuiquanghuy.englishquiz_app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import vn.nguyenbuiquanghuy.englishquiz_app.R;

public class SettingFragment extends Fragment {
    EditText edTime, edQues;
    Button btnTimeMinus, btnTimePlus, btnQuesMinus, btnQuesPlus, btnSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        edTime=view.findViewById(R.id.edTime);
        edQues=view.findViewById(R.id.edQuestions);
        btnTimeMinus=view.findViewById(R.id.btnTimeMinus);
        btnTimePlus=view.findViewById(R.id.btnTimePlus);
        btnQuesMinus=view.findViewById(R.id.btnQuestionsMinus);
        btnQuesPlus=view.findViewById(R.id.btnQuestionsPlus);
        btnSave=view.findViewById(R.id.btnSave);

        loadSettings();

        btnTimeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edTime, -1);
            }
        });

        btnTimePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edTime, 1);
            }
        });

        btnQuesMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edQues, -1);
            }
        });

        btnQuesPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edQues, 1);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
        return view;
    }

    private void changeValue(EditText editText, int change) {
        try {
            int currentValue = Integer.parseInt(editText.getText().toString());
            int newValue = currentValue + change;
            if (newValue < 1) newValue = 1;
            editText.setText(String.valueOf(newValue));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(),
                    "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadSettings() {
        SharedPreferences preferences = getContext().getSharedPreferences("QuizSettings", MODE_PRIVATE);
        int time = preferences.getInt("timeLimit", 30);
        int questions = preferences.getInt("numberOfQuestions", 10);

        edTime.setText(String.valueOf(time));
        edQues.setText(String.valueOf(questions));
    }

    private void saveSettings() {
        String time = edTime.getText().toString();
        String questions = edQues.getText().toString();
        SharedPreferences preferences = getContext().getSharedPreferences("QuizSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("timeLimit", Integer.parseInt(time));
        editor.putInt("numberOfQuestions", Integer.parseInt(questions));
        editor.apply();
        Toast.makeText(getActivity(), "Setting is saved", Toast.LENGTH_SHORT).show();
    }

}