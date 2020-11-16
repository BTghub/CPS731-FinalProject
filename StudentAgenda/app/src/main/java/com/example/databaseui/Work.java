package com.example.databaseui;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Work")
public class Work {
    @PrimaryKey(autoGenerate = true)
    public int work_id;
    public String course;
    public String workType;
    public String dueDate;
    public String description;

    public Work() {

    }

    @Ignore
    public Work(int wid, String course, String wt, String date, String desc) {
        this.work_id = wid;
        this.course = course;
        this.workType = wt;
        this.dueDate = date;
        this.description = desc;
    }
}