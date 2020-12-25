package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView logout;
    CheckBox checkbox_example;
    TextView checkboxValue;
    TextView category;

    RecyclerView tasks_rv;
    TaskAdapterEx taskAdapter;
    static List<Task> tasksList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {

        }        setContentView(R.layout.activity_home);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomeActivity.this, Login.class));
            }
        });

        tasksList.add(new Task("Study Programming",false,"Home"));
        tasksList.add(new Task("Study",true,"University"));
        tasksList.add(new Task("Programming",true, "laptop"));


        tasks_rv = findViewById(R.id.tasks_rv);
        tasks_rv.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapterEx(this ,tasksList );
        tasks_rv.setAdapter(taskAdapter);

    }


}