package com.example.databaseui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AgendaviewModel extends AndroidViewModel {
    private AgendaRepository mRepo;
    private LiveData<List<Course>> mAllCourses;
    private LiveData<List<Event>> mAllEvents;
    private LiveData<List<Work>> mAllWork;

    public AgendaviewModel(Application app) {
        super(app);
        mRepo = new AgendaRepository(app);
        mAllCourses = mRepo.getmAllCourses();
        mAllEvents = mRepo.getmAllEvents();
        mAllWork = mRepo.getmAllWork();
    }

    LiveData<List<Course>> getAllCourses() { return mAllCourses; }
    LiveData<List<Event>> getAllEvents() { return mAllEvents; }
    LiveData<List<Work>> getAllWork() { return mAllWork; }

    public void insertCourse(Course course) { mRepo.insertCourse(course); }
    public void insertEvent(Event event) { mRepo.insertEvent(event); }
    public void insertWork(Work work) { mRepo.insertWork(work); }
    public void deleteWork(Work work) { mRepo.deleteWork(work); }
}