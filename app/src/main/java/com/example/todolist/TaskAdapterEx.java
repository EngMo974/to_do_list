package com.example.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapterEx extends RecyclerView.Adapter<TaskAdapterEx.TaskVh> {

    Context context;
    List<Task> tasks;

    public TaskAdapterEx(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class TaskVh extends RecyclerView.ViewHolder {

        TextView listType;
        CheckBox checkBox;

        public TaskVh(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox_example);
            listType = itemView.findViewById(R.id.category);
        }

        public void setData(final Task task) {
            checkBox.setText(task.getTaskName());
            checkBox.setSelected(task.isChecked());
        
        }
    }

    @NonNull
    @Override
    public TaskVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_home, parent, false);
        return new TaskVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVh holder, int position) {
        holder.setData(tasks.get(position));
        Task taskEntity = tasks.get(position);
        if (taskEntity.isChecked()) {
            holder.checkBox.setChecked(true);
            holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                taskEntity.setChecked(isChecked);
                holder.checkBox.setSelected(isChecked);
                if (isChecked) {
                    holder.checkBox.setText(taskEntity.getTaskName());
                    holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.checkBox.setText(taskEntity.getTaskName());
                    holder.checkBox.setPaintFlags(holder.checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });
    }


}