package vn.nguyenbuiquanghuy.android_project.Account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import vn.nguyenbuiquanghuy.android_project.MainActivity;
import vn.nguyenbuiquanghuy.android_project.R;


public class Login extends AppCompatActivity {
    EditText loginUser,loginPass;
    Button loginButton;
    TextView signUpReidirect;

    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginUser=findViewById(R.id.txtProfileEmail);
        loginPass=findViewById(R.id.txtPassLogin);
        loginButton=findViewById(R.id.btnLogin);
        signUpReidirect=findViewById(R.id.txtViewSignUp);

        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginUser.getText().toString();
                String pass = loginPass.getText().toString();


                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {


                                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(Login.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPass.setError("Mật khẩu không được trống");
                    }
                } else if (email.isEmpty()) {
                    loginUser.setError("Tên tài khoản khng được trống");
                } else {
                    loginUser.setError("Tên tài khoản không đúng");
                }

            }
        });

        signUpReidirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}