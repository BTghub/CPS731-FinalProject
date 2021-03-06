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
    @Query("SELECT * FROM Event WHERE date=:today")
    LiveData<List<Event>> getTodaysEvents(String today);
    @Query("DELETE FROM Event WHERE event_id=:eid")
    void deleteEvent(int eid);
}
