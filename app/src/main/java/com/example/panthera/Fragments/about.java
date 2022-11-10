package com.example.panthera.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.panthera.R;
import com.squareup.picasso.Picasso;


public class about extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about,container,false);

        TextView singleGuideName = (TextView)view.findViewById(R.id.singleGuideName);
        TextView singleDescription = (TextView)view.findViewById(R.id.singleDescription);
        TextView singleLanguages = (TextView)view.findViewById(R.id.singleLanguages);
        TextView singleContact = (TextView)view.findViewById(R.id.singleContact);
        TextView singleAddress = (TextView)view.findViewById(R.id.singleAddress);

        ImageView singleImage = (ImageView)view.findViewById(R.id.singleImage);

        Picasso.get().load(getActivity().getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.guide)
                .into(singleImage);

        singleGuideName.setText(getActivity().getIntent().getStringExtra("singleGuideName"));
        singleDescription.setText(getActivity().getIntent().getStringExtra("singleDescription"));
        singleLanguages.setText(getActivity().getIntent().getStringExtra("singleLanguages"));
        singleContact.setText(getActivity().getIntent().getStringExtra("singleContact"));
        singleAddress.setText(getActivity().getIntent().getStringExtra("singleAddress"));

        return view;


    }


}