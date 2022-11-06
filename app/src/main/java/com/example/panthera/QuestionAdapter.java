package com.example.panthera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        View view = LayoutInflater.from(context).inflate(R.layout.question_forum_view, parent, false);
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

        TextView questionCategory,questionTitle,question;
        ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questionCategory = itemView.findViewById(R.id.questionCategory);
            questionTitle = itemView.findViewById(R.id.questionTitle);
            question = itemView.findViewById(R.id.question);
            userImage = itemView.findViewById(R.id.userImage);

        }
    }
}
