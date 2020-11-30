package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseDetailsActivity extends AppCompatActivity {
    private AgendaViewModel mAgendaViewModel;
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
                String profSearch = prof.replaceAll(" ", "%20");
                //launch intent to search professor
                Intent search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ratemyprofessors.com/search.jsp?query="+profSearch));
                startActivity(search);
            }
        });

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);
        Button delete_course = findViewById(R.id.btn_delete_course);
        delete_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update local db to reflect changes
                Course course = new Course(title,"");
                mAgendaViewModel.deleteCourse(course);
                finish();
            }
        });
    }
}