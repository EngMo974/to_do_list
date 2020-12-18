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

public class SignUp extends AppCompatActivity {
    private TextView toLoginTxt;
    private Button signup;
    private EditText emailid, passwordd;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_sign_up);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }


        emailid = findViewById(R.id.email);
        passwordd = findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
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
                                              Toast.makeText(SignUp.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                                          } else if (!(email.isEmpty() && password.isEmpty())) {
                                              mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                                  @Override
                                                  public void onComplete(@NonNull Task<AuthResult> task) {
                                                      if (!task.isSuccessful()) {
                                                          Toast.makeText(SignUp.this, "Error in Sign Up Please try again", Toast.LENGTH_SHORT).show();
                                                      } else {
                                                          startActivity(new Intent(SignUp.this, HomeActivity.class));
                                                      }
                                                  }
                                              });
                                          } else {
                                              Toast.makeText(SignUp.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                                          }
                                      }
                                  }


        );
        toLoginTxt =  findViewById(R.id.tologin);
        toLoginTxt.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              startActivity(new Intent(SignUp.this, Login.class));
                                          }
                                      }
        );

    }


}