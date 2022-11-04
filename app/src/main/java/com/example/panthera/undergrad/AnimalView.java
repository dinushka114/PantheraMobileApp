package com.example.panthera.undergrad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.panthera.R;
import com.example.panthera.database.AnimalDBHandler;
import com.example.panthera.models.Animal;
import com.example.panthera.specialist.AnimalAdapter;

import java.util.List;

public class AnimalView extends AppCompatActivity {
    Context context;
    AnimalAdapter animalAdapter;
    List<Animal> animals;
    AnimalDBHandler dbConnection;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_undergrad_dashboard);

        //getting the animal list
        listView=findViewById(R.id.AnimalCusView);
        context=this;
        //dbConnection= new AnimalDBHandler(context);
        //animals=dbConnection.getAllAnimals();
        animalAdapter= new AnimalAdapter(context,R.layout.activity_animal_each,animals);
        listView.setAdapter(animalAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //alert box to view the animals
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Animal ani= animals.get(i);
                String name= ani.getName();
                String science= ani.getScientificName();

                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle(name);
                builder.setMessage(science);

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(context,AnimalEach.class);
                        intent.putExtra("id",ani.getId());
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

    }
}
