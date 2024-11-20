package vn.nguyenbuiquanghuy.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    EditText loginUser,loginPass;
    Button loginButton;
    TextView signUpReidirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginUser=findViewById(R.id.txtEmail);
        loginPass=findViewById(R.id.txtPassLogin);
        loginButton=findViewById(R.id.btnLogin);
        signUpReidirect=findViewById(R.id.txtViewSignUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUser() | !validatePass()){

                }else {
                    checkUser();
                }
            }
        });

        signUpReidirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUser(){
        String user=loginUser.getText().toString();
        if(user.isEmpty()){
            loginUser.setError("Hãy nhập tên người dùng");
            return false;
        }else {
            loginUser.setError(null);
            return true;
        }
    }

    public Boolean validatePass(){
        String pass=loginPass.getText().toString();
        if(pass.isEmpty()){
            loginPass.setError("Hãy nhập mật khẩu");
            return false;
        }else {
            loginPass.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userName=loginUser.getText().toString().trim();
        String password=loginPass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserData =reference.orderByChild("userName").equalTo(userName);

        checkUserData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginUser.setError(null);
                    String passDB=snapshot.child(userName).child("password").getValue(String.class);

                    if(passDB.equals(password)){
                        loginUser.setError(null);
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        loginPass.setError("Mật khẩu không đúng");
                        loginPass.requestFocus();
                    }
                }else {
                    loginUser.setError("Tên người dùng không đúng");
                    loginUser.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}