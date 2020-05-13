package ru.bdim.pictures.application;

import android.app.Application;

import androidx.room.Room;

import ru.bdim.pictures.model.Model;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.preferences.DatePreference;

public class PictureApp extends Application {

    private static Model model;
    private static PictureDatabase database;
    private static DatePreference preference;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, PictureDatabase.class, "pictures")
                .build();
        preference = new DatePreference(this);
        model = new Model();
    }

    public static Model getModel(){
        return model;
    }
    public static PictureDatabase getDatabase(){
        return database;
    }
    public static DatePreference getPreferences() {

        return preference;
    }
    @Override
    public void onTerminate() {
        database.close();
        super.onTerminate();
    }
}