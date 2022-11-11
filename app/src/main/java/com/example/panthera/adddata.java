package com.example.panthera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.panthera.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity
{
    EditText questionTitle,question,purl;
    Button submit;
    ImageView back;
    private Spinner questionCategory;
    ArrayAdapter<String> arrayAdapter;
    String[] categories = {"Mammals","Amphibians","Reptiles","Arthropods"};
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);



        questionCategory=findViewById(R.id.add_questionCategory);
        arrayAdapter = new ArrayAdapter<String>(adddata.this, android.R.layout.simple_spinner_dropdown_item,categories);
        questionCategory.setAdapter(arrayAdapter);



        questionTitle=(EditText)findViewById(R.id.add_questionTitle);
        question=(EditText)findViewById(R.id.add_question);
        purl=(EditText)findViewById(R.id.add_purl);

        back=(ImageView)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("questionCategory",questionCategory.getSelectedItem().toString());
        map.put("questionTitle",questionTitle.getText().toString());
        map.put("question",question.getText().toString());
        map.put("purl",purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("questions").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        questionTitle.setText("");
                        question.setText("");
                        purl.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}