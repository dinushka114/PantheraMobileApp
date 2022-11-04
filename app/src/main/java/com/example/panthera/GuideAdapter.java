package com.example.panthera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {

    ArrayList<GuideModel> list;
    Context context;

    public GuideAdapter(ArrayList<GuideModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.guidelist_studentview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView guide_name, guide_description, guide_address, guide_contact, guide_languages;
        ImageView guide_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guide_name = itemView.findViewById(R.id.guide_name);
            guide_description = itemView.findViewById(R.id.guide_description);
            guide_address = itemView.findViewById(R.id.guide_address);
            guide_contact = itemView.findViewById(R.id.guide_contact);
            guide_languages = itemView.findViewById(R.id.guide_languages);

            guide_image = itemView.findViewById(R.id.guide_image);
        }
    }
}
