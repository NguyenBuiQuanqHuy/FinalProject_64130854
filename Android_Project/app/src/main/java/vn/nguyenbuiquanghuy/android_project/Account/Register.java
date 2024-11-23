package vn.nguyenbuiquanghuy.android_project.Account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.nguyenbuiquanghuy.android_project.R;

public class Register extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;



    private EditText signUpUser,signUpEmail,signUpPass;
    private Button btnSignUp;
    private TextView loginRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        signUpUser=findViewById(R.id.txtUser);
        signUpEmail=findViewById(R.id.txtProfileEmail);
        signUpPass=findViewById(R.id.txtPassword);
        btnSignUp=findViewById(R.id.btnRegister);
        loginRedirect=findViewById(R.id.txtViewLogin);

        auth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=signUpUser.getText().toString().trim();
                String email=signUpEmail.getText().toString().trim();
                String pass=signUpPass.getText().toString().trim();

                // Kiểm tra mật khẩu ít nhất 6 ký tự
                if (pass.length() < 6) {
                    // Hiển thị thông báo lỗi nếu mật khẩu không đủ 6 ký tự
                    signUpPass.setError("Mật khẩu phải có 6 ký tự trở lên");
                    return; // Dừng lại không thực hiện đăng ký
                }
                if (user.isEmpty()){
                    signUpUser.setError("Hãy nhập tên tài khoản");
                }
                if (pass.isEmpty()) {
                    signUpPass.setError("Hãy nhập mật khẩu");
                }
                if (email.isEmpty()){
                    signUpEmail.setError("Hãy nhập Email");
                }else {
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(Register.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(Register.this, Login.class);
                startActivity(register);
            }
        });
    }
}