package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddWork extends AppCompatActivity {
    int d3year, d3month, d3date;

    TextView showDate;
    Spinner courseSelect;
    Spinner workTypeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        //initialize course selection spinner
        courseSelect = (Spinner) findViewById(R.id.sp_work_courseChoice);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.courses, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSelect.setAdapter(adapter1);
        //initialize work type selection spinner
        workTypeSelect = (Spinner) findViewById(R.id.sp_homeworkSelect);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.workTypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workTypeSelect.setAdapter(adapter2);

        showDate = findViewById(R.id.tv_work_date);
        Button pickDate = findViewById(R.id.btn_work_date);
        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dPD = new DatePickerDialog(
                        AddWork.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                d3year = year;
                                d3month = month;
                                d3date = dayOfMonth;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(d3year, d3month, d3date, 0, 0);

                                showDate.setText(DateFormat.format("yyyy-MM-dd", calendar));
                            }
                        }, 2020, 1, 1
                );
                dPD.updateDate(d3year, d3month, d3date);
                dPD.show();
            }
        });

        //Submit Button Functionality
        Button sBtn = (Button) findViewById(R.id.btn_work_submit);
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText description = (EditText) findViewById(R.id.et_work_description);

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(description.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //USE THESE VARIABLES TO STORE DATA
                    String course = courseSelect.getSelectedItem().toString();
                    String wT = workTypeSelect.getSelectedItem().toString();
                    String date = showDate.getText().toString();
                    String desc = description.getText().toString();

                    replyIntent.putExtra("course", course);
                    replyIntent.putExtra("workType", wT);
                    replyIntent.putExtra("date", date);
                    replyIntent.putExtra("description", desc);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}