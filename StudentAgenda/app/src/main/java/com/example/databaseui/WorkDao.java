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
    // Generate rem by using rem = java.time.LocalDate.now().plusDays(x).toString()
    // Compare date strings in java with d1.compareTo(d2) method
    @Query("SELECT * FROM Work WHERE workType='Reading' AND dueDate<=:rem")
    LiveData<List<Work>> getTodaysReadings(String rem);
    @Query("SELECT * FROM Work WHERE workType='Test' AND dueDate<=:rem")
    LiveData<List<Work>> getTodaysTests(String rem);
    @Query("SELECT * FROM Work WHERE workType='Assignment' AND dueDate<=:rem")
    LiveData<List<Work>> getTodaysAssignments(String rem);
    @Query("SELECT * FROM Work WHERE workType='Quiz' AND dueDate<=:rem")
    LiveData<List<Work>> getTodaysQuizzes(String rem);
    @Query("SELECT * FROM Work WHERE workType='Test' AND dueDate<=:remT " +
            "UNION SELECT * FROM Work WHERE workType='Assignment' AND dueDate<=:remA " +
            "UNION SELECT * FROM Work WHERE workType='Quiz' AND dueDate<=:remQ " +
            "UNION SELECT * FROM Work WHERE workType='Reading' AND dueDate<=:remR")
    LiveData<List<Work>> getTodaysWork(String remT, String remA, String remQ, String remR);
}
