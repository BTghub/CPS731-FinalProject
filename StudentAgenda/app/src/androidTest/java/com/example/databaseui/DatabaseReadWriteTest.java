package com.example.databaseui;

import android.app.Application;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.textui.TestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.internal.matchers.And;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseReadWriteTest {
    private CourseDao cDao;
    private EventDao eDao;
    private WorkDao wDao;
    private AgendaRoomDatabase db;

    @Rule
    public InstantTaskExecutorRule iTER = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AgendaRoomDatabase.class).allowMainThreadQueries().build();
        cDao = db.courseDao();
        eDao = db.eventDao();
        wDao = db.workDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeCourse() throws Exception {
        Course course = new Course("", "");
        course.title = "CPS731";
        course.prof = "Dr. Manar";
        cDao.insert(course);
        List<Course> courses = LiveDataTestUtil.getValue(cDao.getAllCourses());
        assertThat(courses.get(0).title, equalTo(course.title));
    }

    @Test
    public void removeCourse() throws Exception {
        Course course = new Course("", "");
        course.title = "CPS710";
        course.prof = "Sophie Quigley";
        cDao.insert(course);
        List<Course> courses = LiveDataTestUtil.getValue(cDao.getAllCourses());
        assertThat(courses.size(), equalTo(1));
        cDao.deleteCourse("CPS710");
        courses = LiveDataTestUtil.getValue(cDao.getAllCourses());
        assertThat(courses.size(), equalTo(0));
    }

    @Test
    public void writeEvent() throws Exception {
        Event event = new Event();
        event.title = "CPS731 Final Lecture";
        event.location = "https://zoom.us/id=389745902345";
        event.date = "2020-12-02";
        eDao.insert(event);
        List<Event> events = LiveDataTestUtil.getValue(eDao.getAllEvents());
        assertThat(events.get(0).title, equalTo(event.title));
        assertThat(events.get(0).location, equalTo(event.location));
        assertThat(events.get(0).date, equalTo(event.date));
    }

    @Test
    public void removeEvent() throws Exception {
        Event event = new Event();
        event.title = "CPS803 Guest Lecture";
        event.location = "https://zoom.us/id=978268972455";
        event.date = "2020-12-04";
        eDao.insert(event);
        List<Event> events = LiveDataTestUtil.getValue(eDao.getAllEvents());
        assertThat(events.size(), equalTo(1));
        Event oldEvent = LiveDataTestUtil.getValue(eDao.getAllEvents()).get(0);
        eDao.deleteEvent(oldEvent.event_id);
        events = LiveDataTestUtil.getValue(eDao.getAllEvents());
        assertThat(events.size(), equalTo(0));
    }

    @Test
    public void writeWork() throws Exception {
        Work work = new Work();
        work.course = "CPS731";
        work.workType= "Assignment";
        work.dueDate = "2020-12-02";
        wDao.insert(work);
        List<Work> tasks = LiveDataTestUtil.getValue(wDao.getAllWork());
        assertThat(tasks.get(0).course, equalTo(work.course));
        assertThat(tasks.get(0).workType, equalTo(work.workType));
        assertThat(tasks.get(0).dueDate, equalTo(work.dueDate));
    }

    @Test
    public void removeWork() throws Exception {
        Work work = new Work();
        work.course = "CPS721";
        work.workType= "Test";
        work.dueDate = "2020-12-10";
        wDao.insert(work);
        List<Work> tasks = LiveDataTestUtil.getValue(wDao.getAllWork());
        assertThat(tasks.size(), equalTo(1));
        Work oldWork = LiveDataTestUtil.getValue(wDao.getAllWork()).get(0);
        wDao.deleteEntry(oldWork.work_id);
        tasks = LiveDataTestUtil.getValue(wDao.getAllWork());
        assertThat(tasks.size(), equalTo(0));
    }

}