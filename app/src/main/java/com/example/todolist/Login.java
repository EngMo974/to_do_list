package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextView toSignUp;
    private Button login;
    private EditText emailid, passwordd;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }
        mAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        passwordd = findViewById(R.id.password);
        login = findViewById(R.id.login);

        toSignUp = (TextView) findViewById(R.id.create_account);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String password = passwordd.getText().toString();
                if (email.isEmpty()) {
                    emailid.setError("Please Enter Your Email");
                    emailid.requestFocus();
                } else if (password.isEmpty()) {
                    passwordd.setError("Please Enter Your Password");
                    emailid.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {
                    passwordd.setError("Please Enter Your Password");
                    Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Can't Login", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(Login.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        toSignUp.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(Login.this, SignUp.class);
                                            startActivity(intent);
                                        }
                                    }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(Login.this, "You Are Login", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, HomeActivity.class));
        }else {
            Toast.makeText(Login.this, "Please SignUp", Toast.LENGTH_SHORT).show();
        }

    }
}