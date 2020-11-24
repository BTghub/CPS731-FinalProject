package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDetailsActivity extends AppCompatActivity {
    private AgendaViewModel mAgendaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        // Receive data
        Intent i = getIntent();

        final int eid = i.getIntExtra("id",-1);
        final String title = i.getStringExtra("title");
        final String location = i.getStringExtra("location");
        final String date = i.getStringExtra("date");
        final String start = i.getStringExtra("start");
        final String end = i.getStringExtra("end");

        TextView titleTxt = findViewById(R.id.txteTitle);
        TextView locTxt = findViewById(R.id.txteLocation);
        TextView dateTxt = findViewById(R.id.txteDate);
        TextView startTxt = findViewById(R.id.txtFrom);
        TextView endTxt = findViewById(R.id.txtTo);

        titleTxt.setText("Title: " + title);
        locTxt.setText("Location: " + location);
        startTxt.setText("From: " + start);
        endTxt.setText("To: " + end);
        dateTxt.setText("Date: " + date);

        Button delete_event = findViewById(R.id.btn_delete_event);
        delete_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update local db to reflect changes
                Event event = new Event();
                event.event_id = eid;
                mAgendaViewModel.deleteEvent(event);
                finish();
            }
        });
    }
}