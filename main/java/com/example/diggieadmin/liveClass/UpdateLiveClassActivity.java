package com.example.diggieadmin.liveClass;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diggieadmin.R;
import com.example.diggieadmin.faculty.UpdateFaculty;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateLiveClassActivity extends AppCompatActivity {


    private EditText updateCourseCode, updateCourseTitle, updateLiveClassLink, updateClassTime;
    private Button updateClassBtn, deleteClassBtn;
    private  final int REQ =1;
    private String courseCode, courseTitle, liveClassLink, classTime,  uniqueKey, category;

    private ProgressDialog pd;
    private  StorageReference storageReference;
    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_liveclass);

        //showing valus from another activity
        courseCode = getIntent().getStringExtra("courseCode");
        courseTitle = getIntent().getStringExtra("courseTitle");
        liveClassLink = getIntent().getStringExtra("liveClassLink");
        classTime = getIntent().getStringExtra("classTime");



        updateCourseCode = findViewById(R.id.updateCourseCode);
        updateCourseTitle = findViewById(R.id.updateCourseTitle);
        updateLiveClassLink = findViewById(R.id.updateLiveClassLink);
        updateClassTime = findViewById(R.id.updateClassTime);

        updateClassBtn = findViewById(R.id.updateClassBtn);
        deleteClassBtn = findViewById(R.id.deleteClassBtn);

        pd = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference().child("ClassLink");
        storageReference = FirebaseStorage.getInstance().getReference();

        uniqueKey = getIntent().getStringExtra("key");
        category = getIntent().getStringExtra("category");



        updateCourseCode.setText(courseCode);
        updateCourseTitle.setText(courseTitle);
        updateLiveClassLink.setText(liveClassLink);
        updateClassTime.setText(classTime);



        updateClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseCode = updateCourseCode.getText().toString();
                courseTitle = updateCourseTitle.getText().toString();
                liveClassLink = updateLiveClassLink.getText().toString();
                classTime = updateClassTime.getText().toString();

                checkValidation();
            }
        });

        deleteClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateLiveClassActivity.this);
                builder.setMessage("Do you want to Delete this Class ?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteData();
                            }

                        }

                );
                builder.setNegativeButton(
                        "NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }
                );


                AlertDialog dialog = null;
                try {
                    dialog = builder.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (dialog != null){
                    dialog.show();
                }
            }
        });


    }

    private void deleteData() {

        reference.child(category).child(uniqueKey).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateLiveClassActivity.this, "Class deleted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateLiveClassActivity.this,UpdateClassLink.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateLiveClassActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkValidation() {
        if(courseCode.isEmpty()){
            updateCourseCode.setError("Empty");
            updateCourseCode.requestFocus();
        }else if (courseTitle.isEmpty()){
            updateCourseTitle.setError("Empty");
            updateCourseTitle.requestFocus();
        }else if (liveClassLink.isEmpty()){
            updateLiveClassLink.setError("Empty");
            updateLiveClassLink.requestFocus();
        }else if (classTime.isEmpty()){
            updateClassTime.setError("Empty");
            updateClassTime.requestFocus();
        }else{
            updateData();
        }

    }


    private void updateData() {

        HashMap hp = new HashMap();

        hp.put("courseCode",courseCode);
        hp.put("courseTitle",courseTitle);
        hp.put("liveClassLink",liveClassLink);
        hp.put("classTime",classTime);


        reference.child(category).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateLiveClassActivity.this, "Class updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateLiveClassActivity.this,UpdateClassLink.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateLiveClassActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }



}