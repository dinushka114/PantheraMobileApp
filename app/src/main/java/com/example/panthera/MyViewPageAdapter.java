package com.example.panthera;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.panthera.Fragments.about;
import com.example.panthera.Fragments.photos;
import com.example.panthera.Fragments.reviews;
import com.example.panthera.Fragments.videos;

public class MyViewPageAdapter extends FragmentStateAdapter {

    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new about();
            case 1:
                return new photos();
            case 2:
                return new videos();
            case 3:
                return new reviews();
            default:
                return new about();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
