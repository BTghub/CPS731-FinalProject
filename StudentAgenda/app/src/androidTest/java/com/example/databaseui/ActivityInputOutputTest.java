package com.example.databaseui;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AgendaRoomDatabase.INSTANCE = Room.inMemoryDatabaseBuilder(context, AgendaRoomDatabase.class).build();
    }

    @After
    public void closeDb() throws IOException {
        AgendaRoomDatabase.INSTANCE.close();
    }

    @Test
    public void launchAddCourse() {
        onView(withId(R.id.btn_add_course)).perform(click());
        onView(withId(R.id.et_course_title)).check(matches(isDisplayed()));

        onView(withId(R.id.et_course_title)).perform(typeText("CPS731"), closeSoftKeyboard());
        onView(withId(R.id.et_course_prof)).perform(typeText("Dr. Manar"), closeSoftKeyboard());
        onView(withId(R.id.btn_course_submit)).perform(click());
    }

    @Test
    public void viewCourses() {
        CourseDao cDao = AgendaRoomDatabase.INSTANCE.courseDao();
        Course c1 = new Course("CPS731", "Dr.Manar");
        Course c2 = new Course("CPS710", "Sophie Quigley");
        cDao.insert(c1);
        cDao.insert(c2);
        onView(withId(R.id.btn_view_courses)).perform(click());
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText("CPS731"))));
    }

    @Test
    public void launchAddWork() {
        onView(withId(R.id.btn_add_work)).perform(click());
        onView(withId(R.id.et_work_description)).check(matches(isDisplayed()));
    }

    @Test
    public void viewTodaysWork() {
        WorkDao wDao = AgendaRoomDatabase.INSTANCE.workDao();
        Work w1 = new Work();
        w1.course = "CPS731";
        w1.workType = "Assignment";
        w1.dueDate = "2020-12-03";
        w1.description = "A5";
        Work w2 = new Work();
        w2.course = "CPS731";
        w2.workType = "Quiz";
        w2.dueDate = "2020-12-03";
        w2.description = "Quiz 5";
        wDao.insert(w1);
        wDao.insert(w2);
        onView(withId(R.id.btn_view_work)).perform(click());
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(containsString("Quiz")))));
        pressBack();
        onView(withId(R.id.btn_todays_work)).perform(click());
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(containsString("Assignment")))));
    }

    @Test
    public void launchAddEvent() {
        onView(withId(R.id.btn_add_event)).perform(click());
        onView(withId(R.id.et_event_loc)).check(matches(isDisplayed()));
    }

    @Test
    public void viewTodaysEvents() {
        EventDao eDao = AgendaRoomDatabase.INSTANCE.eventDao();
        Event e1 = new Event();
        e1.title = "Final Lecture";
        e1.location = "https://zoom.us?id=8975892475";
        e1.date = "2020-12-02";
        e1.startTime = "10:00AM";
        e1.endTime = "12:00PM";
        Event e2 = new Event();
        e2.title = "Presentations Day 1";
        e2.location = "https://zoom.us?id=8975892475";
        e2.date = "2020-11-30";
        e2.startTime = "1:00PM";
        e2.endTime = "2:00PM";
        eDao.insert(e1);
        eDao.insert(e2);
        onView(withId(R.id.btn_view_events)).perform(click());
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(containsString("Final Lecture")))));
        pressBack();
        onView(withId(R.id.btn_todays_events)).perform(click());
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.scrollTo(hasDescendant(withText(containsString("Presentations Day 1")))));
    }

    @Test
    public void viewAndEditSettings() {
        // View Settings
        onView(withId(R.id.btn_settings)).perform(click());
        onView(withId(R.id.txtSettings)).check(matches(isDisplayed()));
        // Edit Settings
        onView(withId(R.id.btn_edit_settings)).perform(click());
        onView(withId(R.id.editEmail)).perform(typeText("test@email.com"), closeSoftKeyboard());
        onView(withId(R.id.editTestDays)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.editAssignmentDays)).perform(typeText("4"), closeSoftKeyboard());
        onView(withId(R.id.editReadingDays)).perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.editQuizDays)).perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.btn_save_settings)).perform(click());
        onView(withId(R.id.txtSettings)).check(matches(withText(containsString("Email: test@email.com"))));
    }

}
