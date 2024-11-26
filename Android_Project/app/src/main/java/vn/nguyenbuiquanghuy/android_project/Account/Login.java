package vn.nguyenbuiquanghuy.android_project.Account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import vn.nguyenbuiquanghuy.android_project.MainActivity;
import vn.nguyenbuiquanghuy.android_project.R;

public class Login extends AppCompatActivity {
    EditText loginUser, loginPass;
    Button loginButton;
    TextView signUpRedirect;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        loginUser = findViewById(R.id.txtProfileEmail);
        loginPass = findViewById(R.id.txtPassLogin);
        loginButton = findViewById(R.id.btnLogin);
        signUpRedirect = findViewById(R.id.txtViewSignUp);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu người dùng
                String email = loginUser.getText().toString().trim();
                String pass = loginPass.getText().toString().trim();

                // Kiểm tra xem email và mật khẩu có hợp lệ không
                if (email.isEmpty()) {
                    loginUser.setError("Tên tài khoản không được trống");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    loginUser.setError("Tên tài khoản không hợp lệ");
                    return;
                }

                if (pass.isEmpty()) {
                    loginPass.setError("Mật khẩu không được trống");
                    return;
                }

                // Thực hiện đăng nhập với Firebase
                auth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            public void onFailure(@NonNull Exception e) {
                                // Hiển thị thông báo lỗi đăng nhập
                                Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
