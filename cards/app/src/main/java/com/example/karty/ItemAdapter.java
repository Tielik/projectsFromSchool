package com.example.karty;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Ta klasa jest adapterem dla RecyclerView, który wyświetla listę kursów.
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Courses> courseList;
    private final Context context;

    // Konstruktor inicjalizujący listę kursów i kontekst.
    public ItemAdapter(ArrayList<Courses> courseList, MainActivity context) {
        this.courseList = courseList;
        this.context = context;
    }

    // Tworzy nowy ViewHolder, który jest kontenerem dla elementów widoku.

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_card,parent,false);
        return new ViewHolder(view);
    }
    // Wiąże dane z widokiem.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    Courses course = courseList.get(position);


    holder.CourseName.setText(course.getCourseName());
    holder.groupName.setText(course.getGroupName());
    holder.dateOfCourse.setText(course.getCourseDate());
    holder.timeOfCourse.setText(course.getHours());
    holder.textPlaceView.setText(course.getPlaceName());


    //// Ustawiamy nasłuchiwacz kliknięć.
        holder.itemView.setOnClickListener(view -> {
            Intent intent= new Intent(context,DetailsActivity.class);
            //wyświetla szczegoły po kliknięciu
            intent.putExtra("groupName",course.getGroupName());
            intent.putExtra("courseName",course.getCourseName());
            intent.putExtra("numberOfClasses",course.getNumberOfClasses());
            intent.putExtra("classDuration",course.getClassDuration());
            intent.putExtra("startDate",course.getStartDate());
            intent.putExtra("endDate",course.getEndDate());
            intent.putExtra("placeName",course.getPlaceName());
            intent.putExtra("hours",course.getHours());
            intent.putExtra("position",position);

            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView CourseName,groupName,dateOfCourse,timeOfCourse,textPlaceView;

        ViewHolder(View itemView){
            super(itemView);
            CourseName=itemView.findViewById(R.id.CourseName);
            groupName=itemView.findViewById(R.id.groupName);
            dateOfCourse=itemView.findViewById(R.id.dateOfCourse);
            timeOfCourse=itemView.findViewById(R.id.timeOfCourse);
            textPlaceView=itemView.findViewById(R.id.textPlaceView);
        }
    }
}
