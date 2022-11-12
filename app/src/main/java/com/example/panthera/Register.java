package com.example.panthera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.panthera.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText usernameEdit , emailEdit , passwordEdit;
    Button registerBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        usernameEdit = findViewById(R.id.usernameEditText);
        emailEdit = findViewById(R.id.emailEditText);
        passwordEdit = findViewById(R.id.passwordEditText);

        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progressBar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();


                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email , password)
                        .addOnCompleteListener(Register.this, (OnCompleteListener<AuthResult>) task -> {
                            if(task.isSuccessful()){
                                User user = new User(username , email , password);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(task1 -> {
                                            if(task1.isSuccessful()){
                                                Toast.makeText(getApplicationContext() , "User is registered successful" , Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.INVISIBLE);
                                                startActivity(new Intent(Register.this , Login.class));
                                            }else{
                                                Toast.makeText(getApplicationContext() , "Something went wrong" , Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);


                                            }
                                        });
                            }
                        });
            }
        });
    }
}