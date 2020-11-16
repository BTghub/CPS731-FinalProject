package com.example.databaseui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class ViewCourses extends AppCompatActivity {
    private AgendaviewModel mAgendaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CourseListAdapter adapter = new CourseListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaviewModel.class);

        mAgendaViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable final List<Course> course) {
                // Update the cached copy of the words in the adapter.
                adapter.setCourses(course);
            }
        });
    }
}