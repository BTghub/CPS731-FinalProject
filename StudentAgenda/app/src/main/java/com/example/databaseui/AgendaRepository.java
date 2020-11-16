package com.example.databaseui;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AgendaRepository {
    private CourseDao mCourseDao;
    private LiveData<List<Course>> mAllCourses;
    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;
    private WorkDao mWorkDao;
    private LiveData<List<Work>> mAllWork;

    AgendaRepository(Application app) {
        AgendaRoomDatabase db = AgendaRoomDatabase.getDatabase(app);
        mCourseDao = db.courseDao();
        mAllCourses = mCourseDao.getAllCourses();
        mEventDao = db.eventDao();
        mAllEvents = mEventDao.getAllEvents();
        mWorkDao = db.workDao();
        mAllWork = mWorkDao.getAllWork();
    }

    LiveData<List<Course>> getmAllCourses() {
        return mAllCourses;
    }
    LiveData<List<Event>> getmAllEvents() {
        return mAllEvents;
    }
    LiveData<List<Work>> getmAllWork() {
        return mAllWork;
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
