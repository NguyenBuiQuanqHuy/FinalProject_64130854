package vn.nguyenbuiquanghuy.android_project.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.nguyenbuiquanghuy.android_project.R;

public class HomeFragment extends Fragment {

    EditText quizID;
    EditText quizTitle;
    Button startButton;
    Button createButton;
    TextView solvedquiz;
    TextView myquiz;
    TextView username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        quizID = view.findViewById(R.id.edtRoomID);
        quizTitle = view.findViewById(R.id.edtRoomTilte);
        startButton = view.findViewById(R.id.btnStart);
        createButton = view.findViewById(R.id.btnCreate);
        solvedquiz = view.findViewById(R.id.textViewKQ);
        myquiz = view.findViewById(R.id.textViewMyQuiz);
        username = view.findViewById(R.id.tvUserName);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String nameDB = bundle.getString("name"); // Lấy tên người dùng từ Bundle
            username.setText(nameDB); // Gán tên người dùng vào TextView
            Toast.makeText(getActivity(), "thành công", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
