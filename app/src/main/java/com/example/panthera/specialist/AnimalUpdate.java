package com.example.panthera.specialist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.panthera.R;
import com.example.panthera.models.Animal;
import com.example.panthera.database.AnimalDBHandler;

public class AnimalUpdate extends AppCompatActivity {

    //variable declaration
    private EditText name, sciname, desc;
    private Button update, italic;
    Context context;
    private AnimalDBHandler dbHandler;
    Animal animal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_update);

        context = this;
        //dbHandler = new AnimalDBHandler(context);

        //mapping the elements
        name = findViewById(R.id.editTitle);
        sciname = findViewById(R.id.addscientific);
        desc=findViewById(R.id.adddesc);
        update=findViewById(R.id.btnupdateanimal);
        italic=findViewById(R.id.button);

        //get a single animal for the relevant animal id
        final String id = getIntent().getStringExtra("id");
        //animal = dbHandler.getSingleAnimal(Integer.parseInt(id));

        //getting user updated data
        name.setText(String.valueOf(animal.getName()));
        sciname.setText(animal.getScientificName());
        desc.setText(animal.getDescription());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //passing the newly edited data with the button
                String decText = desc.getText().toString();
                String Science = sciname.getText().toString();
                String Title= name.getText().toString();

                if (Title.equals("")||Science.equals("")||decText.equals("")){
                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
                }

                else{
                    //update the animal details
                    Animal animal1 = new Animal(Integer.parseInt(id),Title,Science,decText);
                    //int state = dbHandler.updateSingleAnimal(animal1);
                    startActivity(new Intent(context, AnimalDashboard.class));
                }
            }
        });
    }
}
