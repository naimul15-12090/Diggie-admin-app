package com.example.diggieadmin.liveClass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diggieadmin.R;
import com.example.diggieadmin.faculty.TeacherData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddLiveClass extends AppCompatActivity {


    private EditText addCourseCode, addCourseTitle, addLiveClassLink, addClassTime;
    private Spinner  addClassDay;
    private Button addClassBtn;

    private final int REQ = 1;
    private ProgressDialog pd;
    private DatabaseReference reference, dbRef;
    private StorageReference storageReference;
    private String courseCode, courseTitle, liveClassLink, classTime;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_liveclass);


        addCourseCode = findViewById(R.id.addCourseCode);
        addCourseTitle = findViewById(R.id.addCourseTitle);
        addLiveClassLink = findViewById(R.id.addLiveClassLink);
        addClassTime = findViewById(R.id.addClassTime);
        addClassDay = findViewById(R.id.addClassDay);
        addClassBtn = findViewById(R.id.addClassBtn);

        pd = new ProgressDialog(this);
        reference = FirebaseDatabase.getInstance().getReference().child("ClassLink");
        storageReference = FirebaseStorage.getInstance().getReference();



        String[] items = new String[] {"Select Class Day", "Saturday", "Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        addClassDay.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        addClassDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = addClassDay.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {
        courseCode = addCourseCode.getText().toString();
        courseTitle = addCourseTitle.getText().toString();
        liveClassLink = addLiveClassLink.getText().toString();
        classTime = addClassTime.getText().toString();

        if(courseCode.isEmpty()){
            addCourseCode.setError("Empty");
            addCourseCode.requestFocus();
        }else if (courseTitle.isEmpty()){
            addCourseTitle.setError("Empty");
            addCourseTitle.requestFocus();
        }else if (liveClassLink.isEmpty()){
            addLiveClassLink.setError("Empty");
            addLiveClassLink.requestFocus();
        }else if (classTime.isEmpty()){
            addClassTime.setError("Empty");
            addClassTime.requestFocus();
        }else if(category.equals("Select Class Day")){
            Toast.makeText(this, "Please provide Class Day", Toast.LENGTH_SHORT).show();
        }else{
            pd.setMessage("Uploading...");
            pd.show();
            uploadData();
        }
    }



    private void uploadData() {
        dbRef = reference.child(category);
        final String uniquekey = dbRef.push().getKey();

        LiveClassData liveClassData = new LiveClassData(courseCode,courseTitle,liveClassLink,classTime,uniquekey);

        dbRef.child(uniquekey).setValue(liveClassData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(AddLiveClass.this, "Class Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AddLiveClass.this, "Something went Wrong!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}