package vn.nguyenbuiquanghuy.englishquiz_app.Fragment;

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

        // Thiết lập sự kiện cho nút giảm thời gian
        btnTimeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edTime, -1);
            }
        });

        // Thiết lập sự kiện cho nút tăng thời gian
        btnTimePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edTime, 1);
            }
        });

        // Thiết lập sự kiện cho nút giảm số câu hỏi
        btnQuesMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edQues, -1);
            }
        });

        // Thiết lập sự kiện cho nút tăng số câu hỏi
        btnQuesPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeValue(edQues, 1);
            }
        });

        // Thiết lập sự kiện lưu cài đặt
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
        return view;
    }

    // Phương thức để thay đổi giá trị
    private void changeValue(EditText editText, int change) {
        try {
            int currentValue = Integer.parseInt(editText.getText().toString());
            int newValue = currentValue + change;
            if (newValue < 1) newValue = 1;
            editText.setText(String.valueOf(newValue));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức lưu cài đặt
    private void saveSettings() {
        String time = edTime.getText().toString();
        String questions = edQues.getText().toString();
        Toast.makeText(getActivity(), "Cài đặt đã được lưu\nThời gian: " + time + " giây\nSố câu hỏi: " + questions, Toast.LENGTH_SHORT).show();
    }
}