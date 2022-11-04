package com.example.panthera.specialist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.panthera.R;
import com.example.panthera.models.Animal;

import java.util.List;

public class AnimalAdapter extends ArrayAdapter<Animal> {

    private Context context;
    private int resource;
    List<Animal> animalList;

    //animal adapter constructor
    public AnimalAdapter(Context context, int resource, List<Animal> animalList){
        super(context,resource,animalList);
        this.context=context;
        this.resource=resource;
        this.animalList=animalList;
    }

    //get view method
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.tvtitle);
        TextView scientificName = row.findViewById(R.id.tvdecription);
        ImageView imageView = row.findViewById(R.id.imageview);

        // anima;s [obj1,obj2,obj3]
        Animal animal = animalList.get(position);

        byte[] data = animal.getAnimalImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        imageView.setImageBitmap(bmp);

        title.setText(animal.getName());
        scientificName.setText(animal.getScientificName());

        return row;
    }
}
