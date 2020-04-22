package ru.bdim.pictures.detail.application;

import android.app.Application;

import ru.bdim.pictures.model.Model;

public class PictureApp extends Application {

    private static PictureApp instance;
    private Model model;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        model = new Model();
    }
    public static PictureApp getInstance(){
        return instance;
    }
    public Model getModel(){
        return instance.model;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
