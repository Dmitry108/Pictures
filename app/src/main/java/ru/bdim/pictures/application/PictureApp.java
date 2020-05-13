package ru.bdim.pictures.application;

import android.app.Application;

import androidx.room.Room;

import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.preferences.DatePreference;

public class PictureApp extends Application {

    private static PictureApp instance;
    private static AppComponent component;

//    private static PictureDatabase database;
//    private static DatePreference preference;

    public static PictureApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        database = Room.databaseBuilder(this, PictureDatabase.class, "pictures")
//                .build();
//        preference = new DatePreference(this);
        instance = this;
        component = createComponent();
    }

    public static AppComponent getComponent(){
        return component;
    }
    private static AppComponent createComponent(){
        return DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

//    public static PictureDatabase getDatabase(){
//        return database;
//    }
//    public static DatePreference getPreferences() {
//        return preference;
//    }

    @Override
    public void onTerminate() {
//        database.close();
        instance = null;
        super.onTerminate();
    }
}