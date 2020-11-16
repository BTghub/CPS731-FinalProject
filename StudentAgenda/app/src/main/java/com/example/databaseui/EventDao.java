package com.example.databaseui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Event event);
    @Query("DELETE FROM Event")
    void deleteAll();
    @Query("SELECT * from Event ORDER BY date ASC")
    LiveData<List<Event>> getAllEvents();
}
