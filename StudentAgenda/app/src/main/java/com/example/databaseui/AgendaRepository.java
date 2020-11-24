package com.example.databaseui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.time.LocalDate;
import java.util.List;

public class AgendaRepository {
    private CourseDao mCourseDao;
    private LiveData<List<Course>> mAllCourses;
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;
    private LiveData<List<Event>> mTodayEvents;
    private WorkDao mWorkDao;
    private LiveData<List<Work>> mAllWork;

    @RequiresApi(api = Build.VERSION_CODES.O)
    AgendaRepository(Application app) {
        AgendaRoomDatabase db = AgendaRoomDatabase.getDatabase(app);
        mCourseDao = db.courseDao();
        mAllCourses = mCourseDao.getAllCourses();

        mEventDao = db.eventDao();
        mAllEvents = mEventDao.getAllEvents();
        mTodayEvents = mEventDao.getTodaysEvents(LocalDate.now().toString());

        mWorkDao = db.workDao();
        mAllWork = mWorkDao.getAllWork();
    }

    LiveData<List<Course>> getmAllCourses() {
        return mAllCourses;
    }
    LiveData<List<Event>> getmAllEvents() {
        return mAllEvents;
    }
    LiveData<List<Event>> getmTodayEvents() { return mTodayEvents; }
    LiveData<List<Work>> getmAllWork() {
        return mAllWork;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    LiveData<List<Work>> getTodaysWork(int T, int A, int Q, int R) {
        String rT = LocalDate.now().plusDays(T).toString();
        String rA = LocalDate.now().plusDays(A).toString();
        String rQ = LocalDate.now().plusDays(Q).toString();
        String rR = LocalDate.now().plusDays(R).toString();
        return mWorkDao.getTodaysWork(rT,rA,rQ,rR);
    }

    public void insertCourse(Course course) {
        new insertCourseAsyncTask(mCourseDao).execute(course);
    }
    public void insertEvent(Event event) {
        new insertEventAsyncTask(mEventDao).execute(event);
    }
    public void insertWork(Work work) {
        new insertWorkAsyncTask(mWorkDao).execute(work);
    }

    private static class insertCourseAsyncTask extends AsyncTask<Course,Void,Void> {
        private CourseDao mAsyncTaskDao;

        insertCourseAsyncTask(CourseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Course... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteCourse(Course course) {
        new deleteCourseAsyncTask(mCourseDao).execute(course);
    }
    private static class deleteCourseAsyncTask extends AsyncTask<Course,Void,Void> {
        private CourseDao mAsyncTaskDao;

        deleteCourseAsyncTask(CourseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Course... params) {
            mAsyncTaskDao.deleteCourse(params[0].title);
            return null;
        }
    }

    private static class insertEventAsyncTask extends AsyncTask<Event,Void,Void> {
        private EventDao mAsyncTaskDao;

        insertEventAsyncTask(EventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Event... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteEvent(Event event) {
        new deleteEventAsyncTask(mEventDao).execute(event);
    }
    private static class deleteEventAsyncTask extends AsyncTask<Event,Void,Void> {
        private EventDao mAsyncTaskDao;

        deleteEventAsyncTask(EventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Event... params) {
            mAsyncTaskDao.deleteEvent(params[0].event_id);
            return null;
        }
    }

    private static class insertWorkAsyncTask extends AsyncTask<Work,Void,Void> {
        private WorkDao mAsyncTaskDao;

        insertWorkAsyncTask(WorkDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Work... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteWork(Work work) {
        new deleteWorkAsyncTask(mWorkDao).execute(work);
    }
    private static class deleteWorkAsyncTask extends AsyncTask<Work,Void,Void> {
        private WorkDao mAsyncTaskDao;

        deleteWorkAsyncTask(WorkDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Work... params) {
            mAsyncTaskDao.deleteEntry(params[0].work_id);
            return null;
        }
    }
}
