package com.example.panthera;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddQuestion extends AppCompatActivity {

    TextView questionTitle,question;
    Button sendBtn,uploadimgbtn,uploadvideobtn;
    ImageView userImage,questionImage, uploadImage;
    LinearLayout linearLayout;
    Uri ImageUri;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private Spinner questionCategory;
    ArrayAdapter<String> arrayAdapter;
    String[] categories = {"Mammals","Amphibians","Reptiles","Arthropods"};
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guide);

        questionCategory = findViewById(R.id.questionCategory);


        arrayAdapter = new ArrayAdapter<String>(AddQuestion.this, android.R.layout.simple_spinner_dropdown_item,categories);
        questionCategory.setAdapter(arrayAdapter);

        questionCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Toast.makeText(AddQuestion.this,"You select "+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Sending the question..");
        dialog.setCanceledOnTouchOutside(false);

        questionCategory = findViewById(R.id.questionCategory);
        questionTitle = findViewById(R.id.questionTitle);
        question = findViewById(R.id.question);
        userImage = findViewById(R.id.userImage);

        
        linearLayout = findViewById(R.id.linearLayout);

        sendBtn = findViewById(R.id.sendBtn);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                linearLayout.setVisibility(View.VISIBLE);
                userImage.setVisibility(View.GONE);

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

                final StorageReference reference = firebaseStorage.getReference().child("question")
                        .child(System.currentTimeMillis() + "");

                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                    QuestionModel model = new QuestionModel();
                                    model.setUserImage(uri.toString());

                                    model.setQuestionCategory(questionCategory.getSelectedItem().toString());
                                    model.setQuestionTitle(questionTitle.getText().toString());
                                    model.setQuestion(question.getText().toString());

                                    
                                    database.getReference().child("question").push().setValue(model)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    Toast.makeText(AddQuestion.this, "Question Send Successfully!", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    dialog.dismiss();
                                                    Toast.makeText(AddQuestion.this, "Error Occurred", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddQuestion.this, "Permission Denied", Toast.LENGTH_SHORT).show();
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
                userImage.setImageURI(ImageUri);
        }
        else{
            Toast.makeText(AddQuestion.this, "please insert user image", Toast.LENGTH_SHORT).show();
        }
    }
}