package com.example.panthera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.panthera.specialist.AnimalAdd;
import com.example.panthera.specialist.AnimalDashboard;
import com.example.panthera.undergrad.AnimalView;

public class MainActivity extends AppCompatActivity {

    Button specialist, undergrad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        specialist = findViewById(R.id.button2);
        undergrad = findViewById(R.id.button3);

        specialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimalDashboard.class));
            }
        });

        undergrad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimalView.class));
            }
        });
    }
}