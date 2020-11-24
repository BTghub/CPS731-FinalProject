package com.example.databaseui;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.time.LocalDate;
import java.util.List;

public class AgendaViewModel extends AndroidViewModel {
    private AgendaRepository mRepo;
    private LiveData<List<Course>> mAllCourses;
    private LiveData<List<Event>> mAllEvents;
    private LiveData<List<Event>> mTodaysEvents;
    private LiveData<List<Work>> mAllWork;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public AgendaViewModel(Application app) {
        super(app);
        mRepo = new AgendaRepository(app);
        mAllCourses = mRepo.getmAllCourses();
        mAllEvents = mRepo.getmAllEvents();
        mTodaysEvents = mRepo.getmTodayEvents();
        mAllWork = mRepo.getmAllWork();
    }

    LiveData<List<Course>> getAllCourses() { return mAllCourses; }
    LiveData<List<Event>> getAllEvents() { return mAllEvents; }
    LiveData<List<Event>> getTodayEvents() { return mTodaysEvents; }
    LiveData<List<Work>> getAllWork() { return mAllWork; }
    @RequiresApi(api = Build.VERSION_CODES.O)
    LiveData<List<Work>> getTodaysWork(int T, int A, int Q, int R) {
        return mRepo.getTodaysWork(T,A,Q,R);
    }

    public void insertCourse(Course course) { mRepo.insertCourse(course); }
    public void deleteCourse(Course course) { mRepo.deleteCourse(course); }
    public void insertEvent(Event event) { mRepo.insertEvent(event); }
    public void deleteEvent(Event event) { mRepo.deleteEvent(event); }
    public void insertWork(Work work) { mRepo.insertWork(work); }
    public void deleteWork(Work work) { mRepo.deleteWork(work); }
}