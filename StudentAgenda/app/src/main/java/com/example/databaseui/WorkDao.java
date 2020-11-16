package com.example.databaseui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Work work);
    @Query("DELETE FROM Work")
    void deleteAll();
    @Query("SELECT * from Work ORDER BY course ASC")
    LiveData<List<Work>> getAllWork();
    @Query("DELETE FROM Work WHERE work_id=:id")
    void deleteEntry(int id);
}
