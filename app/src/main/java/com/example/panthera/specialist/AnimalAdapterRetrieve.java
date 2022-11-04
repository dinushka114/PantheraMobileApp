package com.example.panthera.specialist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.panthera.R;
import com.example.panthera.models.Animal;

import java.util.List;

public class AnimalAdapterRetrieve extends RecyclerView.Adapter<AnimalAdapterRetrieve.ViewHolder> {

    private Context mcontext;
    private List<Animal> animals;

    public AnimalAdapterRetrieve(Context mcontext, List<Animal> animals) {
        this.mcontext = mcontext;
        this.animals = animals;
    }

    @NonNull
    @Override//convert layout into a view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create viewholder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_animal_single_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapterRetrieve.ViewHolder holder, int position) {
        //loading data
        holder.title.setText(animals.get(position).getName());
        holder.scientificName.setText(animals.get(position).getScientificName());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView scientificName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.SingleAniTitle);
            scientificName = itemView.findViewById(R.id.SingleAniDesc);
        }
    }
}
