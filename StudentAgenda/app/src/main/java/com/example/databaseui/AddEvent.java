package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Button;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    //ints for usage in TimePickerDialog and DatePickerDialog
    int t1Hour,t1Minute;
    int d1Year, d1Month, d1DayOf;

    TextView showStartTime;
    TextView showEndTime;
    TextView showDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Button pickDate = (Button) findViewById(R.id.btn_event_date);
        Button pickStartT = (Button) findViewById(R.id.btn_startTime);
        Button pickEndT = (Button) findViewById(R.id.btn_endTime);

        showStartTime = (TextView) findViewById(R.id.tv_startTime);
        showEndTime = (TextView) findViewById(R.id.tv_endTime);
        showDate = (TextView) findViewById(R.id.tv_event_date);

        //PickStartTime Button Functionality
        pickStartT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog tPD = new TimePickerDialog(
                        AddEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                showStartTime.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                tPD.updateTime(t1Hour, t1Minute);
                tPD.show();

            }
        });

        //PickEndTime Button Functionality
        pickEndT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog tPD = new TimePickerDialog(
                        AddEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hour, t1Minute);
                                showEndTime.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                tPD.updateTime(t1Hour, t1Minute);
                tPD.show();
            }
        });

        //Date Button Functionality
        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dPD = new DatePickerDialog(
                        AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                d1Year = year;
                                d1Month = month;
                                d1DayOf = dayOfMonth;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(d1Year, d1Month, d1DayOf, 0, 0);

                                showDate.setText(DateFormat.format("yyyy-MM-dd", calendar));
                            }
                        }, 2020, 1, 1
                );
                dPD.updateDate(d1Year, d1Month, d1DayOf);
                dPD.show();
            }
        });

        //Submit Button Functionality
        Button sBtn = (Button) findViewById(R.id.btn_event_submit);
        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = (EditText) findViewById(R.id.et_event_title);
                EditText location = (EditText) findViewById(R.id.et_event_loc);

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(title.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //USE THESE VARIABLES TO STORE DATA
                    String tt = title.getText().toString();
                    String loc = location.getText().toString();
                    String date = showDate.getText().toString();
                    String stime = showStartTime.getText().toString();
                    String etime = showEndTime.getText().toString();

                    replyIntent.putExtra("title", tt);
                    replyIntent.putExtra("location", loc);
                    replyIntent.putExtra("date", date);
                    replyIntent.putExtra("startTime", stime);
                    replyIntent.putExtra("endTime", etime);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}