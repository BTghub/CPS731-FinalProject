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

public class ViewEvents extends AppCompatActivity implements EventListAdapter.ListItemClickListener {
    private AgendaViewModel mAgendaViewModel;
    EventListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new EventListAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        mAgendaViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable final List<Event> event) {
                // Update the cached copy of the words in the adapter.
                adapter.setEvents(event);
            }
        });
    }

    @Override
    public void onListItemClick(int position) {
        Intent i = new Intent(ViewEvents.this, EventDetailsActivity.class);
        Event obj = adapter.mEvents.get(position);
        // Add item details to intent
        i.putExtra("id", obj.event_id);
        i.putExtra("title", obj.title);
        i.putExtra("location", obj.location);
        i.putExtra("start", obj.startTime);
        i.putExtra("end", obj.endTime);
        i.putExtra("date", obj.date);

        this.startActivity(i);
    }
}