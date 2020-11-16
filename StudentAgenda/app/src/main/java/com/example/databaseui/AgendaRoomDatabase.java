package com.example.databaseui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Course.class, Event.class, Work.class}, version = 4, exportSchema = false)
public abstract class AgendaRoomDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract EventDao eventDao();
    public abstract WorkDao workDao();

    public static AgendaRoomDatabase INSTANCE;

    public static AgendaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AgendaRoomDatabase.class) { // Needed since the db will be created on it's own thread
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AgendaRoomDatabase.class, "agenda_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                }
            };
}
