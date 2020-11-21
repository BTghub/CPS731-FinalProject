package com.example.databaseui;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import java.util.List;

public class ViewTodaysWork extends AppCompatActivity implements WorkListAdapter.ListItemClickListener {
    private AgendaViewModel mAgendaViewModel;
    private WorkListAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_todays_work);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WorkListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sp = getSharedPreferences(MainActivity.prefs, Context.MODE_PRIVATE);
        int T = sp.getInt("test_days", 4);
        int A = sp.getInt("assignment_days", 3);
        int Q = sp.getInt("quiz_days", 1);
        int R = sp.getInt("reading_days", 1);

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);
        mAgendaViewModel.getTodaysWork(T,A,Q,R).observe(this, new Observer<List<Work>>() {
            @Override
            public void onChanged(@Nullable final List<Work> work) {
                // Update the cached copy of the words in the adapter.
                adapter.setWork(work);
            }
        });
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ViewTodaysWork.this, WorkDetailsActivity.class);
        Work obj = adapter.mWork.get(position);
        // Add item details to intent
        i.putExtra("id", obj.work_id);
        i.putExtra("course", obj.course);
        i.putExtra("type", obj.workType);
        i.putExtra("date", obj.dueDate);
        i.putExtra("desc", obj.description);

        this.startActivity(i);
    }
}