package com.example.panthera.Fragments;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panthera.ClickedItemActivity;
import com.example.panthera.MainActivity;
import com.example.panthera.MyViewPageAdapter;
import com.example.panthera.R;

import java.util.ArrayList;
import java.util.Base64;

public class photos extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photos,container,false);

        GridView gridView = (GridView) view.findViewById(R.id.gridView);

        String[] names = {"trip1", "trip2", "trip3", "trip4", "trip5"};
        int[] images = {
                R.drawable.trip1,
                R.drawable.trip2,
                R.drawable.trip3,
                R.drawable.trip4,
                R.drawable.trip5
        };

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedName = names[i];
                int selectedImage = images[i];

                startActivity(new Intent(getActivity(), ClickedItemActivity.class).putExtra("name",selectedName).putExtra("image",selectedImage));

            }
        });

        CustomAdapter customAdapter = new CustomAdapter(names, images, getActivity());
        gridView.setAdapter(customAdapter);

        return view;
    }

    public class CustomAdapter extends BaseAdapter {

        private String[] imageNames;
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(String[] imageNames, int[] imagesPhoto, Context context) {
            this.imageNames = imageNames;
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if(view == null) {
                view = layoutInflater.inflate(R.layout.row_data, viewGroup, false);
            }

            TextView tvName = view.findViewById(R.id.tvName);
            ImageView imageView = view.findViewById(R.id.imageView);

            tvName.setText(imageNames[i]);
            imageView.setImageResource(imagesPhoto[i]);



            return view;
        }
    }
}