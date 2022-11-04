package com.example.diggieadmin.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.diggieadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;



    private RecyclerView cseDepartment,othersDepartment,DepartmentHead,Advisor;
    private LinearLayout cseNoData,othersDepartmentNoData,DepartmentHeadNoData,AdvisorNoData;
    private List<TeacherData> list1,list2,list3,list4;
    private TeacherAdapter adapter;

    private DatabaseReference reference , dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        cseDepartment = findViewById(R.id.cseDepartment);
        othersDepartment = findViewById(R.id.othersDepartment);
        DepartmentHead = findViewById(R.id.DepartmentHead);
        Advisor = findViewById(R.id.Advisor);

        cseNoData = findViewById(R.id.cseNoData);
        othersDepartmentNoData = findViewById(R.id.othersDepartmentNoData);
        DepartmentHeadNoData = findViewById(R.id.DepartmentHeadNoData);
        AdvisorNoData = findViewById(R.id.AdvisorNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");

        cseDepartment();
        othersDepartment();
        DepartmentHead();
        Advisor();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this,AddTeachers.class));
            }
        });
    }

    private void cseDepartment() {
        dbRef = reference.child("Department of CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    cseNoData.setVisibility(View.VISIBLE);
                    cseDepartment.setVisibility(View.GONE);

                }else{

                    cseNoData.setVisibility(View.GONE);
                    cseDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    cseDepartment.setHasFixedSize(true);
                    cseDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1,UpdateFaculty.this,"Department of CSE");
                    cseDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void othersDepartment() {
        dbRef = reference.child("others Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    othersDepartmentNoData.setVisibility(View.VISIBLE);
                    othersDepartment.setVisibility(View.GONE);

                }else{

                    othersDepartmentNoData.setVisibility(View.GONE);
                    othersDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    othersDepartment.setHasFixedSize(true);
                    othersDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2,UpdateFaculty.this, "others Department");
                    othersDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void DepartmentHead() {
        dbRef = reference.child("Department Head");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    DepartmentHeadNoData.setVisibility(View.VISIBLE);
                    DepartmentHead.setVisibility(View.GONE);

                }else{

                    DepartmentHeadNoData.setVisibility(View.GONE);
                    DepartmentHead.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    DepartmentHead.setHasFixedSize(true);
                    DepartmentHead.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3,UpdateFaculty.this,"Department Head");
                    DepartmentHead.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Advisor() {
        dbRef = reference.child("Advisor");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    AdvisorNoData.setVisibility(View.VISIBLE);
                    Advisor.setVisibility(View.GONE);

                }else{

                    AdvisorNoData.setVisibility(View.GONE);
                    Advisor.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    Advisor.setHasFixedSize(true);
                    Advisor.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4,UpdateFaculty.this,"Advisor");
                    Advisor.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}