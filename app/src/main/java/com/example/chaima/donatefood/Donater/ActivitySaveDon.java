package com.example.chaima.donatefood.Donater;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chaima.donatefood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;

public class ActivitySaveDon extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // private static final int RESULT_OK = ;
    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextHealthCondition;
    EditText editTextPickUpTime;
    EditText editTextAdr;


    TextView txtViewNumberPerson;
    TextView txtViewPickUpTime;

    CheckBox checkBoxWithPackage;
    CheckBox checkBoxCancelAssignement;

    Spinner spNumberPerson;

    FloatingActionButton btnSaveDon;

    Switch switchCancelAssignement;

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    //ImageButton imageButtonPicture ;
    private Uri file;
    private Uri fileUri;
    private ImageButton takePictureButton ;
    private ImageView imageView;

    Spinner spinnerNumber;

    Boolean switchState;

    Don don ;

    Bitmap imageBitmap ;


    String timeStamp ;
    String itemNumberPerson;

    //Firebase
    DatabaseReference reference;
    FirebaseStorage storage;
    StorageReference storageReference;
    //StorageReference ref ;

    private String id ;

    Button btnTest ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_don);

        editTextTitle = (EditText) findViewById(R.id.ediTxtTitle);
        editTextDescription = (EditText) findViewById(R.id.ediTxtDescription);
        editTextHealthCondition = (EditText) findViewById(R.id.ediTxtHealthCondition);
        editTextPickUpTime = (EditText) findViewById(R.id.editTxtPickUpTime);
        editTextAdr = (EditText) findViewById(R.id.ediTxtSetAdr);

        txtViewNumberPerson = (TextView) findViewById(R.id.txtViewNumberPerson);
        txtViewPickUpTime = (TextView) findViewById(R.id.txtViewPickUpTime);

        editTextPickUpTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);


                timePickerDialog = new TimePickerDialog(ActivitySaveDon.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        editTextPickUpTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
         /*FragmentManager fragmentMg = getFragmentManager() ;
                            FragmentTransaction fragmentTrs= fragmentMg.beginTransaction();
                            fragmentTrs.replace(R.id.FrameLayout_save_don, Fragment.newInstance());
                            fragmentTrs.addToBackStack(null);
                            fragmentTrs.commit();*/




        spinnerNumber = (Spinner) findViewById(R.id.spinnerNumberPerson);
        spinnerNumber.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numberPerson, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerNumber.setAdapter(adapter);

        switchCancelAssignement = (Switch) findViewById(R.id.switchcancelAssignement);
        switchCancelAssignement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchState = switchCancelAssignement.isChecked();

            }
        });

        btnSaveDon = (FloatingActionButton)findViewById(R.id.btnSaveDon);

        takePictureButton = (ImageButton) findViewById(R.id.button_image);
        imageView = (ImageView) findViewById(R.id.imageview);

       /* btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    Toast.makeText(ActivitySaveDon.this, "You are connected to Internet", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ActivitySaveDon.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        reference = FirebaseDatabase.getInstance().getReference("Don");


        btnSaveDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFood();
            }
        });

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    takePicture();
                } else {
                    requestCameraAndStoragePermission();
                }
            }
        });


       // btnupload = (Button) findViewById(R.id.button);
        /*btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*StorageReference ref = FirebaseStorage.getInstance().getReference();
                StorageReference filepath = ref.child("images").child(file.getLastPathSegment());
                filepath.putFile(file) ;*//*
                encodeBitmapAndSaveToFirebase(imageBitmap);
            }

        });*/

    }





    public void saveFood() {
        String title = editTextTitle.getText().toString().trim();
        String desc = editTextDescription.getText().toString().trim();
        String number = editTextTitle.getText().toString().trim();
        String timepickup = editTextTitle.getText().toString().trim();


        if ((!TextUtils.isEmpty(title)) && (!TextUtils.isEmpty(timepickup))) {

            if (isOnline()) {
                // Toast.makeText(ActivitySaveDon.this, "picture !!!", Toast.LENGTH_SHORT).show();
                id = reference.push().getKey();
                if(imageBitmap == null){
                    Toast.makeText(ActivitySaveDon.this, "Your photo not added  " , LENGTH_LONG).show();
                    don = new Don(id, editTextTitle.getText().toString(), editTextDescription.getText().toString(), editTextHealthCondition.getText().toString(), editTextAdr.getText().toString(), itemNumberPerson, editTextPickUpTime.getText().toString(), switchState);
                }else {
                    String imgEncode = encodeBitmapAndSaveToFirebase(imageBitmap);
                  //  Toast.makeText(ActivitySaveDon.this, "You are connected to Internet " , LENGTH_LONG).show();
                    don = new Don(id, editTextTitle.getText().toString(), editTextDescription.getText().toString(), editTextHealthCondition.getText().toString(), editTextAdr.getText().toString(), itemNumberPerson, editTextPickUpTime.getText().toString(), switchState, imgEncode);
                }
                    reference.child(id).setValue(don).addOnCompleteListener(new OnCompleteListener<Void>() {
                    /*  private static final String TAG = "test";*/

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivitySaveDon.this, "Don Food it's saved with success ", LENGTH_LONG).show();
                            Intent intent = new Intent(ActivitySaveDon.this, ProfileDonater.class);
                            startActivity(intent);

                        } else {
                            //  Log.d(TAG, task.getException().getMessage());
                            Toast.makeText(ActivitySaveDon.this, "Don Food it's not saved  ", LENGTH_LONG).show();
                        }
                    }
                });
            }else{

                Toast.makeText(ActivitySaveDon.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            }
        } else {
            if ((TextUtils.isEmpty(title)) || (TextUtils.isEmpty(desc))) {
                editTextTitle.setError("Required Title or Description about Food");
            }
            if (TextUtils.isEmpty(timepickup)) editTextTitle.setError("Required Time Pick up");

            Toast.makeText(this, "Please enter necessary informations about Food", LENGTH_LONG).show();

        }
           /* if(imageBitmap == null){
                Toast.makeText(this, "Your photo not added  "  + switchState, LENGTH_LONG).show();
            }else {*/

               /* editTextTitle.setText("");
                editTextDescription.setText("");
                editTextPickUpTime.setText("");
                editTextAdr.setText("");*/
               /* reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        for (DataSnapshot donSnapshot : dataSnapshot.getChildren()) {
                            //   donList.add(donSnapshot.getValue(Don.class));
                            Log.d("onDataChange", "onDataChange: " + donSnapshot.toString());

                            if (donSnapshot.child(id).getValue() != null) {
                                Toast.makeText(ActivitySaveDon.this, "Don Food it's saved with success "  + switchState, LENGTH_LONG).show();
                               *//* Intent intent = new Intent(ActivitySaveDon.this, FragmentDonation.class);
                                startActivity(intent);*//*
                            } else {
                                Toast.makeText(ActivitySaveDon.this, "Don Food it's not saved  " , LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

            /*} else {
                Toast.makeText(ActivitySaveDon.this, "its saved without picture !", Toast.LENGTH_SHORT).show();
            }*/

        /*}else{
            Toast.makeText(ActivitySaveDon.this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
        }*/
       // }
        /*
        }*/

    }

    public String encodeBitmapAndSaveToFirebase(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        String imageEncoded = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
               // reference.child("imageUrl").setValue(imageEncoded);
        return imageEncoded ;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
                Toast.makeText(this, "Take Photo", LENGTH_LONG).show();


            }
        }

    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Camera");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("Camera", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    private void requestCameraAndStoragePermission() {
        ActivityCompat.requestPermissions(ActivitySaveDon.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(ActivitySaveDon.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                file = data.getData();
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);


            }

        } else {
            Toast.makeText(this, "Result Failed ", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // On selecting a spinner item
        itemNumberPerson = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        //Toast.makeText(this, "selected" + itemNumberPerson , LENGTH_LONG ).show();



    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

/*
private void uploadImage() {


            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = FirebaseStorage.getInstance().getReference();
            StorageReference filepath = ref.child("images").child(file.getLastPathSegment());
            filepath.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }

*/


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
