package vn.nguyenbuiquanghuy.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
    }
    public void MainScreen(View v){
        Intent main=new Intent(Login.this,MainScreen.class);
        startActivity(main);
    }
    public void RegisterScreen(View v){
        Intent register=new Intent(Login.this,Register.class);
        startActivity(register);
    }
}