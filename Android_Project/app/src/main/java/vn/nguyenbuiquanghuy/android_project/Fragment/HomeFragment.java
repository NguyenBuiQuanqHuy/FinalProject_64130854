package vn.nguyenbuiquanghuy.android_project.Fragment;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.nguyenbuiquanghuy.android_project.R;

public class HomeFragment extends Fragment {
    private String userUID;
    private String name;

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

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        quizID = view.findViewById(R.id.edtRoomID);
        quizTitle = view.findViewById(R.id.edtRoomTilte);
        startButton = view.findViewById(R.id.btnStart);
        createButton = view.findViewById(R.id.btnCreate);
        solvedquiz = view.findViewById(R.id.textViewKQ);
        myquiz = view.findViewById(R.id.textViewMyQuiz);
        username = view.findViewById(R.id.tvUserName);



        return view;
    }
}