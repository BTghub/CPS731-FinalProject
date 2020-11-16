package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class AddCourse extends AppCompatActivity {
    EditText titleEdit;
    EditText profEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Button sBtn = (Button) findViewById(R.id.btn_course_submit);

        sBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                titleEdit = (EditText) findViewById(R.id.et_course_title);
                profEdit = (EditText) findViewById(R.id.et_course_prof);

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(titleEdit.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //USE THESE TO ACCESS THE DATA FROM INPUT
                    String title = titleEdit.getText().toString();
                    String prof = profEdit.getText().toString();

                    replyIntent.putExtra("title", title);
                    replyIntent.putExtra("prof", prof);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}