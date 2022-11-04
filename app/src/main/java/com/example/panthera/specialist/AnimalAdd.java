package com.example.panthera.specialist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.panthera.R;
import com.example.panthera.database.AnimalDBHandler;
import com.example.panthera.models.Animal;
import com.squareup.picasso.Picasso;
//import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class AnimalAdd extends AppCompatActivity {

    //variable declaration
    private EditText name, sciname, desc;
    private Button add, italic;
    Context context;
    ImageView imageView;
    private AnimalDBHandler dbHandler;

    //permission to get camera and storage permission
    public  static final int CAMERA_REQUEST=100;
    public  static final int STORAGE_REQUEST=101;

    String cameraPermission[];
    String storagePermission[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_add);

        //mapping the elements
        name = findViewById(R.id.addtitle);
        sciname = findViewById(R.id.addscientific);
        desc = findViewById(R.id.adddesc);
        imageView = findViewById(R.id.animalimage);
        add = findViewById(R.id.btnaddanimal);
        italic = findViewById(R.id.button);

        //getting the db connection
        context =this;
        //dbHandler=new AnimalDBHandler(context);


        //method to choose the image
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int imageView=0;
//                if (imageView==0){
//                    if (!checkCameraPermission()){
//                        requestCameraPermission();
//                    }else {
//                        pickFromGallery();
//                    }
//                }else if (imageView==1){
//                    if (!checkStrogepermission()){
//                        requestStrogePermission();
//                    }else{
//                        pickFromGallery();
//                    }
//                }
//            }
//        });

        //method to add animal
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String SciName = sciname.getText().toString();
                String Desc = desc.getText().toString();

                //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (Name.equals("")||SciName.equals("")||Desc.equals("")){
                    //error if all fields are empty
                    Toast.makeText(context, "Please enter all details", Toast.LENGTH_SHORT).show();
                }

                else { //insert a new row
                    Animal newAnimal = new Animal(Name, SciName, Desc, imageViewToBy(imageView));
//                    Boolean checkanimaladding = dbHandler.insertAnimal(newAnimal);
//
//                    if (checkanimaladding == true) {
//                        Intent i = new Intent(AnimalAdd.this, AnimalDashboard.class);
//                        Toast.makeText(AnimalAdd.this, "Animal added", Toast.LENGTH_LONG).show();
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(AnimalAdd.this, "Insertion was not successful", Toast.LENGTH_LONG).show();
//                    }
                }
            }

        });
    }

    //check camera permission
    private boolean checkCameraPermission() {
        boolean result= ContextCompat.checkSelfPermission(AnimalAdd.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        boolean result1=ContextCompat.checkSelfPermission(AnimalAdd.this,Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    //methods to get image from the phone
    //---------------------------------->
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode){
//            case CAMERA_REQUEST:{
//                if (grantResults.length>0){
//
//                    boolean camera_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
//
//                    if (camera_accepted ){
//                        pickFromGallery();
//
//                    }else {
//                        Toast.makeText(this, "Please enable permission", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }break;
//            case STORAGE_REQUEST:{
//                if (grantResults.length>0){
//                    boolean storage_accepted=grantResults[0]==PackageManager.PERMISSION_GRANTED;
//
//                    if (storage_accepted){
//                        pickFromGallery();
//                    }else {
//                        Toast.makeText(this, "Please enable permission", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }break;
//        }
//    }

    //request permission for the storage
    private void requestStrogePermission() {
        ActivityCompat.requestPermissions(AnimalAdd.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, STORAGE_REQUEST);
    }

    //check if permissions are given
    private boolean checkStrogepermission() {
        boolean result= ContextCompat.checkSelfPermission(AnimalAdd.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    //method to pick images from the gallery
//    private void pickFromGallery() {
//        CropImage.activity().start(this);
//    }

    //request camera permission
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(AnimalAdd.this,new String[] { Manifest.permission.CAMERA }, CAMERA_REQUEST);
    }

    //convert image into byte array
    public static byte[] imageViewToBy(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageInByte = baos.toByteArray();
        return imageInByte;
    }

    //load image into imageview
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//                Picasso.with(AnimalAdd.this).load(resultUri).into(imageView);
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
//                Exception error = result.getError();
//            }
//        }
//  }
}
