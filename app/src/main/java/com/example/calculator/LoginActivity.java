package com.example.calculator;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (db.checkUser(username, password)) {
                    User loggedInUser = db.getUser(username);
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login Berhasil")
                            .setMessage("Selamat Datang, " + loggedInUser.getUsername() + "!")
                            .setPositiveButton("OK", (dialog, which) -> {
                                Intent intent = new Intent(LoginActivity.this, Laman_Utama.class);
                                startActivity(intent);
                                finish();
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Login Gagal")
                            .setMessage("Username atau Password salah!")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });


        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
