package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Receive data
        Intent i = getIntent();

        final String title = i.getStringExtra("title");
        final String prof = i.getStringExtra("prof");

        TextView titleTxt = findViewById(R.id.txtTitle);
        TextView profTxt = findViewById(R.id.txtProf);

        titleTxt.setText("Course: " + title);
        profTxt.setText("Prof: " + prof);

        Button view_profile = findViewById(R.id.btn_view_profile);
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //access profile from the web
            }
        });
    }
}