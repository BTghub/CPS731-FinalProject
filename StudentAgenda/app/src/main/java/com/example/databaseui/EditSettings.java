package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSettings extends AppCompatActivity {
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_settings);

        sp = getSharedPreferences(MainActivity.prefs, Context.MODE_PRIVATE);

        final EditText email = findViewById(R.id.editEmail);
        final EditText testDays = findViewById(R.id.editTestDays);
        final EditText assignmentDays = findViewById(R.id.editAssignmentDays);
        final EditText quizDays = findViewById(R.id.editQuizDays);
        final EditText readingDays = findViewById(R.id.editReadingDays);

        Button save = findViewById(R.id.btn_save_settings);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_email = email.getText().toString();
                int new_Tdays = Integer.parseInt(testDays.getText().toString());
                int new_Adays = Integer.parseInt(assignmentDays.getText().toString());
                int new_Qdays = Integer.parseInt(quizDays.getText().toString());
                int new_Rdays = Integer.parseInt(readingDays.getText().toString());
                // update preferences
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("user_email", new_email);
                editor.putInt("test_days", new_Tdays);
                editor.putInt("assignment_days", new_Adays);
                editor.putInt("quiz_days", new_Qdays);
                editor.putInt("reading_days", new_Rdays);
                editor.commit();

                Toast.makeText(EditSettings.this, "Settings Successfully Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditSettings.this, Settings.class);
                startActivity(intent);
            }
        });
    }
}