package com.example.databaseui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewWork extends AppCompatActivity implements WorkListAdapter.ListItemClickListener {
    private AgendaviewModel mAgendaViewModel;
    private WorkListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_work);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WorkListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaviewModel.class);

        mAgendaViewModel.getAllWork().observe(this, new Observer<List<Work>>() {
            @Override
            public void onChanged(@Nullable final List<Work> work) {
                // Update the cached copy of the words in the adapter.
                adapter.setWork(work);
            }
        });
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ViewWork.this, WorkDetailsActivity.class);
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