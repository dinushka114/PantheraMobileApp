package com.example.panthera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button loginBtn;
    TextView signUpTextView;
    EditText usernameText , passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpTextView = findViewById(R.id.signUpText);
        usernameText = findViewById(R.id.usernameEditText);
        passwordText = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginBtn);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this , Register.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            String email= "";
            String password = "";

            @Override
            public void onClick(View view) {
                try{
                     email = usernameText.getText().toString();
                     password = passwordText.getText().toString();

                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(email + " " +  password);


//                Toast.makeText(getApplicationContext() , "User is registered successful" , Toast.LENGTH_LONG).show();

                mAuth.signInWithEmailAndPassword(email , password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext() , "User login successful" , Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext() , "User is login failed" , Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


    }
}