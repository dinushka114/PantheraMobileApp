package com.example.panthera;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ReviewPopup extends DialogFragment {
    public static final String TAG = "MyReviewDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage("Hello")
                .setPositiveButton("Hi", (dialog, which) -> {} )
                .create();
    }

}
