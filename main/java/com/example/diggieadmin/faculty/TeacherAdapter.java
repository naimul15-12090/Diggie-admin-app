package com.example.diggieadmin.faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diggieadmin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeacherAdapter  extends RecyclerView.Adapter<TeacherAdapter.TeacherViewAdapter> {

    private List<TeacherData> list;
    private Context context;
    private String category;

    public TeacherAdapter(List<TeacherData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;

    }

    @NonNull
    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent,false);
        return new TeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewAdapter holder, int position) {

        final TeacherData item = list.get(position);
        holder.name.setText(item.getName());
        holder.initial.setText(item.getInitial());
        holder.teacherId.setText(item.getTeacherId());
        holder.phone.setText(item.getPhone());
        holder.email.setText(item.getEmail());
        try {
            Picasso.get().load(item.getImage()).into(holder.teacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.teacherUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sending data from this activity to another activity
                Intent intent = new Intent(context,UpdateTeacherActivity.class);
                intent.putExtra("name",item.getName());
                intent.putExtra("email",item.getEmail());
                intent.putExtra("initial",item.getInitial());
                intent.putExtra("teacherId",item.getTeacherId());
                intent.putExtra("phone",item.getPhone());
                intent.putExtra("image",item.getImage());
                intent.putExtra("key",item.getKey());
                intent.putExtra("category", category);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TeacherViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, initial,phone,teacherId;
        private Button teacherUpdateBtn;
        private ImageView teacherImage;


        public TeacherViewAdapter(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.teacherName);
            initial = itemView.findViewById(R.id.teacherInitial);
            email = itemView.findViewById(R.id.teacherEmail);
            teacherId = itemView.findViewById(R.id.teacherId);
            phone = itemView.findViewById(R.id.teacherPhone);
            teacherUpdateBtn = itemView.findViewById(R.id.teacherUpdateBtn);
            teacherImage = itemView.findViewById(R.id.teacherImage);

        }
    }
}
