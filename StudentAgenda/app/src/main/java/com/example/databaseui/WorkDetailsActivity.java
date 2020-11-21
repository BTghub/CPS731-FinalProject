package com.example.databaseui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WorkDetailsActivity extends AppCompatActivity {
    private AgendaViewModel mAgendaViewModel;
    private DocumentReference mDocRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Receive data
        Intent i = getIntent();

        final int wid = i.getIntExtra("id",-1);
        final String course = i.getStringExtra("course");
        final String type = i.getStringExtra("type");
        final String date = i.getStringExtra("date");
        final String desc = i.getStringExtra("desc");

        TextView courseTxt = findViewById(R.id.txtCourse);
        TextView typeTxt = findViewById(R.id.txtType);
        TextView dateTxt = findViewById(R.id.txtDate);
        TextView descTxt = findViewById(R.id.txtDesc);

        courseTxt.setText("Course: " + course);
        typeTxt.setText("Homework type: " + type);
        dateTxt.setText("Due date: " + date);
        descTxt.setText("Description: " + desc);

        mDocRef = FirebaseFirestore.getInstance().document("CompletedTasks/" + wid);
        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);
        Button markCompleted = findViewById(R.id.btn_completed);
        markCompleted.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // Setup document and upload to cloud FireStore
                Map<String, String> dataToSave = new HashMap<String,String>();
                dataToSave.put("course", course);
                dataToSave.put("type", type);
                dataToSave.put("description", desc);
                dataToSave.put("completedOn", java.time.LocalDate.now().toString());
                mDocRef.set(dataToSave);
                // Update local db to reflect changes
                Work work = new Work();
                work.work_id = wid;
                mAgendaViewModel.deleteWork(work);
                finish();
            }
        });
    }
}