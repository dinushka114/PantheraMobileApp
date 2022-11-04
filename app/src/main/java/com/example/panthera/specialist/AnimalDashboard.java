package com.example.panthera.specialist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.panthera.R;
import com.example.panthera.database.AnimalDBHandler;
import com.example.panthera.models.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalDashboard extends AppCompatActivity {

    //declaring the variables
    private Button add;
    private ListView animalList;
    private TextView animalcount;
    Context context;
    private AnimalDBHandler db; //db connection object
    private List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_specialist_dashboard);
        context = this;

        //mapping elements
        //db = new AnimalDBHandler(getApplicationContext());
        add = findViewById(R.id.add);
        animalList = findViewById(R.id.animallist);
        animalcount = findViewById(R.id.animaldesc);
        animals = new ArrayList<>();

       // animals = db.getAllCostumes(); //getting all the animal lists

        //getting data

        AnimalAdapter adapter = new AnimalAdapter(context,R.layout.activity_animal_each,animals);

        animalList.setAdapter(adapter);

       // int countanimals = db.countCostumes(); //displaying the animal count in the list
        //animalcount.setText("You have "+countanimals+" animals");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //button to add new costumes
                startActivity(new Intent(AnimalDashboard.this,AnimalAdd.class));
            }
        });

        animalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //alert box to chose delete or update option
                final Animal ani = animals.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(ani.getName());
                builder.setMessage(ani.getScientificName());

                builder.setNegativeButton("Delete Animal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,AnimalDelete.class);
                        intent.putExtra("idDel",String.valueOf(ani.getId()));
                        startActivity(intent);
                        //this will redirect to deleteing the animal
                    }
                });
                builder.setNeutralButton("Update Animal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,AnimalUpdate.class);
                        intent.putExtra("id",String.valueOf(ani.getId()));
                        startActivity(intent);
                        //this will redirect to updating the animal
                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
