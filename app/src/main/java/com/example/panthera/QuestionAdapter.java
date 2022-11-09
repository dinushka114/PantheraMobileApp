package com.example.panthera;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    ArrayList<QuestionModel> list;
    Context context;

    public QuestionAdapter(ArrayList<QuestionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel model = list.get(position);

        Picasso.get().load(model.getUserImage()).placeholder(R.drawable.guide).into(holder.userImage);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context , UpdateQuestion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.questionCategory.setText(model.getQuestionCategory());
        holder.questionTitle.setText(model.getQuestionTitle());
        holder.question.setText(model.getQuestion());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SingleQuestionActivity.class);


                intent.putExtra("singleQuestionTitle", model.getQuestionTitle());
                intent.putExtra("singleQuestion", model.getQuestion());
                intent.putExtra("singleQuestionCategory", model.getQuestionCategory());
                intent.putExtra("singleImage", model.getUserImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);



            }




        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView questionCategory,questionTitle,question;
        ImageView userImage;
        Button btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questionCategory = itemView.findViewById(R.id.questionCategory);
            questionTitle = itemView.findViewById(R.id.questionTitle);
            question = itemView.findViewById(R.id.question);
            userImage = itemView.findViewById(R.id.userImage);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }

    }
}
