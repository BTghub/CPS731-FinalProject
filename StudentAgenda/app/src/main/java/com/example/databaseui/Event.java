package com.example.databaseui;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Event")
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int event_id;
    public String title;
    public String location;
    public String date;
    public String startTime;
    public String endTime;

    public Event() {

    }
    @Ignore
    public Event(int eid, String title, String loc, String date, String sTime, String eTime) {
        this.event_id = eid;
        this.title = title;
        this.location = loc;
        this.date = date;
        this.startTime = sTime;
        this.endTime = eTime;
    }
}
