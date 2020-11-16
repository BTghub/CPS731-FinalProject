package com.example.databaseui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);
    @Query("DELETE FROM Course")
    void deleteAll();
    @Query("SELECT * from Course ORDER BY title ASC")
    LiveData<List<Course>> getAllCourses();
}
