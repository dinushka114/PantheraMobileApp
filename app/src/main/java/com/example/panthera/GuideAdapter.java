package com.example.panthera;

import static java.util.Objects.*;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

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

        GuideModel model = list.get(position);

        Picasso.get().
                load(model.getImage1())
                .placeholder(R.drawable.guide)
                .into(holder.guide_image);

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , UpdateGuide.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.guide_name.setText(model.getGuide_name());
        holder.guide_address.setText(model.getGuide_address());
        holder.guide_contact.setText(model.getGuide_contact());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleGuideActivity.class);

                intent.putExtra("singleImage", model.getImage1());
                intent.putExtra("singleGuideName", model.getGuide_name());
                intent.putExtra("singleDescription", model.getGuide_description());
                intent.putExtra("singleLanguages", model.getGuide_languages());
                intent.putExtra("singleContact", model.getGuide_contact());
                intent.putExtra("singleAddress", model.getGuide_address());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

//        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(holder.guide_name.getContext());
//                builder.setTitle("Delete safari guide");
//                builder.setMessage("Are you sure , you want to remove the guide ?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("Safari Guides")
//                                .child(getRef(position).getKey()).removeValue();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                builder.show();
//            }
//        });



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView guide_name, guide_address, guide_contact;
        ImageView guide_image;

        Button editbtn, deletebtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            guide_name = itemView.findViewById(R.id.guide_name);
            guide_address = itemView.findViewById(R.id.guide_address);
            guide_contact = itemView.findViewById(R.id.guide_contact);

            guide_image = itemView.findViewById(R.id.guide_image);

            editbtn = itemView.findViewById(R.id.editbtn);
            deletebtn = itemView.findViewById(R.id.deletebtn);


        }

    }

}


