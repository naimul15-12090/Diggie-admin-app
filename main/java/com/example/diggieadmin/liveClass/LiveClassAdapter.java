package com.example.diggieadmin.liveClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diggieadmin.R;

import java.util.List;

public class LiveClassAdapter extends RecyclerView.Adapter<LiveClassAdapter.ClassLinkViewAdapter > {

    private List<LiveClassData> list;
    private Context context;
    private String category;

    public LiveClassAdapter(List<LiveClassData> list, Context context, String category) {
        this.list= list;
        this.context = context;
        this.category = category;

    }

    @NonNull
    @Override
    public ClassLinkViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.class_link_item_layout, parent,false);
        return new ClassLinkViewAdapter (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassLinkViewAdapter holder, int position) {

        final LiveClassData item = list.get(position);
        holder.courseCode.setText(item.getCourseCode());
        holder.courseTitle.setText(item.getCourseTitle());
        holder.liveClassLink.setText(item.getLiveClassLink());
        holder.classTime.setText(item.getClassTime());


        holder.classLinkUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sending data from this activity to another activity
                Intent intent = new Intent(context,UpdateLiveClassActivity.class);
                intent.putExtra("courseCode",item.getCourseCode());
                intent.putExtra("courseTitle",item.getCourseTitle());
                intent.putExtra("liveClassLink",item.getLiveClassLink());
                intent.putExtra("classTime",item.getClassTime());
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

    public class ClassLinkViewAdapter extends RecyclerView.ViewHolder {

        private TextView courseCode, courseTitle, liveClassLink,classTime;
        private Button classLinkUpdateBtn;



        public ClassLinkViewAdapter(@NonNull View itemView) {
            super(itemView);

            courseCode = itemView.findViewById(R.id.courseCode);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            liveClassLink = itemView.findViewById(R.id.liveClassLink);
            classTime = itemView.findViewById(R.id.classTime);
            classLinkUpdateBtn = itemView.findViewById(R.id.classLinkUpdateBtn);


        }
    }


}
