package com.example.panthera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleGuideActivity extends AppCompatActivity {

    TextView singleGuideName, singleDescription, singleLanguages, singleContact, singleAddress;
    ImageView singleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_guide);

        singleGuideName = findViewById(R.id.singleGuideName);
        singleDescription = findViewById(R.id.singleDescription);
        singleLanguages = findViewById(R.id.singleLanguages);
        singleContact = findViewById(R.id.singleContact);
        singleAddress = findViewById(R.id.singleAddress);

        singleImage = findViewById(R.id.singleImage);

        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.guide)
                .into(singleImage);

        singleGuideName.setText(getIntent().getStringExtra("singleGuideName"));
        singleDescription.setText(getIntent().getStringExtra("singleDescription"));
        singleLanguages.setText(getIntent().getStringExtra("singleLanguages"));
        singleContact.setText(getIntent().getStringExtra("singleContact"));
        singleAddress.setText(getIntent().getStringExtra("singleAddress"));
    }
}





