package vn.nguyenbuiquanghuy.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private EditText signUpUser,signUpEmail,signUpPass;
    private Button btnSignUp;
    private TextView loginRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        signUpUser=findViewById(R.id.txtUser);
        signUpEmail=findViewById(R.id.txtEmail);
        signUpPass=findViewById(R.id.txtPassword);
        btnSignUp=findViewById(R.id.btnRegister);
        loginRedirect=findViewById(R.id.txtViewLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");
                String user=signUpUser.getText().toString().trim();
                String email=signUpEmail.getText().toString().trim();
                String pass=signUpPass.getText().toString().trim();

                Account account=new Account(user,email,pass);
                reference.child(user).setValue(account);
                Toast.makeText(Register.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(Register.this, Login.class);
                startActivity(intent);

            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register=new Intent(Register.this,Login.class);
                startActivity(register);
            }
        });
    }

}