package com.example.panthera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class AddGuide extends AppCompatActivity {

    TextView guide_name, guide_description, guide_address, guide_contact, guide_languages;
    Button uploadvideobtn, uploadimgbtn, addbtn;
    ImageView guide_image, image1;
    LinearLayout linearLayout;
    Uri ImageUri;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guide);

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Guide Adding");
        dialog.setCanceledOnTouchOutside(false);

        guide_name = findViewById(R.id.guide_name);
        guide_description = findViewById(R.id.guide_description);
        guide_address = findViewById(R.id.guide_address);
        guide_contact = findViewById(R.id.guide_contact);
        guide_languages = findViewById(R.id.guide_languages);

        guide_image = findViewById(R.id.guide_image);
        image1 = findViewById(R.id.image1);
        
        linearLayout = findViewById(R.id.linearLayout);

        uploadimgbtn = findViewById(R.id.uploadimgbtn);
        uploadvideobtn = findViewById(R.id.uploadvideobtn);

        addbtn = findViewById(R.id.addbtn);

        guide_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                linearLayout.setVisibility(View.VISIBLE);
                guide_image.setVisibility(View.GONE);

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                final StorageReference reference = firebaseStorage.getReference().child("guide")
                        .child(System.currentTimeMillis() + "");

                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                    GuideModel model = new GuideModel();
                                    model.setImage1(uri.toString());

                                    model.setGuide_name(guide_name.getText().toString());
                                    model.setGuide_address(guide_address.getText().toString());
                                    model.setGuide_contact(guide_contact.getText().toString());
                                    model.setGuide_description(guide_description.getText().toString());
                                    model.setGuide_languages(guide_languages.getText().toString());
                                    
                                    database.getReference().child("guide").push().setValue(model)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    Toast.makeText(AddGuide.this, "Guide Added Successfully!", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    dialog.dismiss();
                                                    Toast.makeText(AddGuide.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                            }
                            
                        });

                    }
                });
            }
        });

    }

    private void UploadImage() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(AddGuide.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
                ImageUri = data.getData();
                image1.setImageURI(ImageUri);
        }
    }
}