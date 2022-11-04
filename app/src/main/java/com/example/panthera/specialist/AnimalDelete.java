package com.example.panthera.specialist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.panthera.R;

import androidx.appcompat.app.AppCompatActivity;

import com.example.panthera.database.AnimalDBHandler;
import com.example.panthera.models.Animal;

public class AnimalDelete extends AppCompatActivity {

    //variable declaration
    private EditText name, sciname, desc;
    private Button delete;
    Context context;
    private AnimalDBHandler dbHandler;
    Animal animal;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_delete);

        //mapping the elements
        name=findViewById(R.id.deletetitle);
        sciname = findViewById(R.id.addscientific);
        desc=findViewById(R.id.adddesc);
        delete=findViewById(R.id.btndeleteanimal);

        context=this;

        animal =new Animal();
        //dbHandler=new AnimalDBHandler(context);


        final String id= getIntent().getStringExtra("idDel");

        //getting animals to relevant animal id
        //animal=dbHandler.getSingleAnimal(Integer.parseInt(id));

        name.setText(animal.getName());
        sciname.setText(animal.getScientificName());
        desc.setText(animal.getDescription());

        //disable editing while deleting
        name.setKeyListener(null);
        sciname.setKeyListener(null);
        desc.setKeyListener(null);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button delete
                //dbHandler.deleteAnimal(animal.getId());
                startActivity(new Intent(context, AnimalDashboard.class));

            }
        });
    }
}
