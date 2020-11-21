package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AgendaViewModel mAgendaViewModel;
    public static final int ADD_COURSE_ACTIVITY_REQUEST_CODE = 1;
    public static final int ADD_EVENT_ACTIVITY_REQUEST_CODE = 2;
    public static final int ADD_WORK_ACTIVITY_REQUEST_CODE = 3;
    public static final String prefs = "MySettings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        //Add Course Button launches AddCourse Activity
        Button btn_add_course = (Button) findViewById(R.id.btn_add_course);
        btn_add_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCourse.class);
                startActivityForResult(intent, ADD_COURSE_ACTIVITY_REQUEST_CODE);
            }
        });

        //Add Event Button launches AddEvent Activity
        Button btn_add_event = (Button) findViewById(R.id.btn_add_event);
        btn_add_event.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEvent.class);
                startActivityForResult(intent, ADD_EVENT_ACTIVITY_REQUEST_CODE);
            }
        });

        //Add Work Button launches AddWork Activity
        Button btn_add_work = (Button) findViewById(R.id.btn_add_work);
        btn_add_work.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddWork.class);
                startActivityForResult(intent, ADD_WORK_ACTIVITY_REQUEST_CODE);
            }
        });

        //View Courses Button launches ViewCourses Activity
        Button btn_view_courses = (Button) findViewById(R.id.btn_view_courses);
        btn_view_courses.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewCourses.class);
                startActivity(intent);
            }
        });

        //View Events Button launches ViewEvents Activity
        Button btn_view_events = (Button) findViewById(R.id.btn_view_events);
        btn_view_events.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewEvents.class);
                startActivity(intent);
            }
        });

        //View Work Button launches ViewWork Activity
        Button btn_view_work = (Button) findViewById(R.id.btn_view_work);
        btn_view_work.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewWork.class);
                startActivity(intent);
            }
        });

        //View Completed Tasks button launches ViewCompletedTasks Activity
        Button btn_view_completed = findViewById(R.id.btn_view_completed);
        btn_view_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewCompletedTasks.class);
                startActivity(intent);
            }
        });

        //Settings button launches Settings Activity
        Button btn_settings = findViewById(R.id.btn_settings);
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        //Todays Events button launches ViewTodaysEvents Activity
        Button btn_todays_events = findViewById(R.id.btn_todays_events);
        btn_todays_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewTodaysEvents.class);
                startActivity(intent);
            }
        });

        //Todays work button launches ViewTodaysWork Activity
        Button btn_todays_work = findViewById(R.id.btn_todays_work);
        btn_todays_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewTodaysWork.class);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_COURSE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Course course = new Course(data.getStringExtra("title"), data.getStringExtra("prof"));
            mAgendaViewModel.insertCourse(course);
        } else if (requestCode == ADD_EVENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Event event = new Event();
            event.title = data.getStringExtra("title");
            event.location = data.getStringExtra("location");
            event.date = data.getStringExtra("date");
            event.startTime = data.getStringExtra("startTime");
            event.endTime = data.getStringExtra("endTime");
            mAgendaViewModel.insertEvent(event);
        } else if (requestCode == ADD_WORK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Work work = new Work();
            work.course = data.getStringExtra("course");
            work.workType = data.getStringExtra("workType");
            work.dueDate = data.getStringExtra("date");
            work.description = data.getStringExtra("description");
            mAgendaViewModel.insertWork(work);
        }else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}