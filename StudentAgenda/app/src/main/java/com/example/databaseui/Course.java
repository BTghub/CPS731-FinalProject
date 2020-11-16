package com.example.databaseui;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Course")
public class Course {
    @PrimaryKey
    @NonNull
    public String title;
    public String prof;
    public Course(@NonNull String title, String prof) { this.title = title; this.prof = prof;}
}
