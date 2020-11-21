package com.example.databaseui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.api.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddWork extends AppCompatActivity {
    int d3year, d3month, d3date;

    TextView showDate;
    Spinner courseSelect;
    Spinner workTypeSelect;

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    private AgendaViewModel mAgendaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        //initialize course selection spinner
        ArrayList<String> courseList = new ArrayList<String>();
        courseSelect = (Spinner) findViewById(R.id.sp_work_courseChoice);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                courseList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSelect.setAdapter(adapter1);

        mAgendaViewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);
        mAgendaViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                for (Course c: courses) {
                    courseList.add(c.title);
                }
                adapter1.notifyDataSetChanged();
            }
        });
        //initialize work type selection spinner
        workTypeSelect = (Spinner) findViewById(R.id.sp_homeworkSelect);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.workTypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workTypeSelect.setAdapter(adapter2);

        Calendar c = Calendar.getInstance();
        final int todayY = c.get(Calendar.YEAR);
        final int todayM = c.get(Calendar.MONTH);
        final int todayD = c.get(Calendar.DAY_OF_MONTH);
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
                        }, todayY, todayM, todayD
                );
                //dPD.updateDate(d3year, d3month, d3date);
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

        //Import Button Functionality
        Button btn_import = findViewById(R.id.btn_photo_import);
        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            }
        });
    }
}