package com.example.databaseui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class ViewCourses extends AppCompatActivity implements CourseListAdapter.ListItemClickListener {
    private AgendaViewModel mAgendaViewModel;
    private CourseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new CourseListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        mAgendaViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(@Nullable final List<Course> course) {
                // Update the cached copy of the words in the adapter.
                adapter.setCourses(course);
            }
        });
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ViewCourses.this, CourseDetailsActivity.class);
        Course obj = adapter.mCourses.get(position);
        // Add item details to intent
        i.putExtra("title", obj.title);
        i.putExtra("prof", obj.prof);

        this.startActivity(i);
    }
}