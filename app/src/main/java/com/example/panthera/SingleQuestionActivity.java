package com.example.panthera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleQuestionActivity extends AppCompatActivity {

    TextView singleQuestionTitle,singleQuestion,singleQuestionCategory;
    ImageView singleImage;
    ImageButton btnEdit,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        singleQuestionTitle = findViewById(R.id.singleQuestionTitle);
        singleQuestion = findViewById(R.id.singleQuestion);
        singleQuestionCategory = findViewById(R.id.singleQuestionCategory);
        singleImage = findViewById(R.id.singleImage);

        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.guide)
                .into(singleImage);

        singleQuestionTitle.setText(getIntent().getStringExtra("singleQuestionTitle"));
        singleQuestion.setText(getIntent().getStringExtra("singleQuestion"));
        singleQuestionCategory.setText(getIntent().getStringExtra("singleQuestionCategory"));




    }


}






