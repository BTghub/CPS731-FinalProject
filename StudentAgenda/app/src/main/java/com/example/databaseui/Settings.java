package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp = getSharedPreferences(MainActivity.prefs, Context.MODE_PRIVATE);
        TextView txtSettings = findViewById(R.id.txtSettings);
        txtSettings.setText(
                "Email: " + sp.getString("user_email", "") + "\n\n" +
                "Days of Reminder\n\n" +
                "Test:\t\t" + sp.getInt("test_days", 4) + "\n\n" +
                "Assignment:\t\t" + sp.getInt("assignment_days", 3) + "\n\n" +
                "Quiz:\t\t" + sp.getInt("quiz_days", 1) + "\n\n" +
                "Reading:\t\t" + sp.getInt("reading_days", 1)
        );

        Button edit_settings = findViewById(R.id.btn_edit_settings);
        edit_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, EditSettings.class);
                startActivity(intent);
            }
        });
    }
}