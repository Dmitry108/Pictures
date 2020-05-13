package ru.bdim.pictures.model;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDao;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.preferences.DatePreference;
import ru.bdim.pictures.model.retrofit.HitRequest;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

public class Model {

    //части модели
    @Inject
    DataInfo dataInfo;
    @Inject
    PicturesRequester internet;
    @Inject
    PictureDatabase database;
    @Inject
    DatePreference preferences;

    public Model(){
//        dataInfo = new DataInfo();
//        internet = new PicturesRequester();
        PictureApp.getComponent().inject(this);
//        database = PictureApp.getDatabase();
//        preferences = PictureApp.getPreferences();
    }

    public int setCurrentChoice (int position, String url) {
        return dataInfo.setCurrentChoice(position, url);
    }

    public String getPicture(){
        return dataInfo.getPicture();
    }

    public int getCurrentPosition(){
        return dataInfo.getCurrentPosition();
    }

    public Observable<HitRequest> loadFromInternet() {
        return internet.loadPictures();
    }

    public Observable<List<PictureEntity>> loadFromDatabase(){
        return database.getDao().getAll();
    }

    public void initArray(int size) {
        dataInfo.initArray(size);
    }
    public boolean isFirstTimePerDay(){
        Date date = new Date();
        if (!date.toString().equals(preferences.getSavedDate())){
           preferences.saveDate(date);
           return true;
        } else {
            return false;
        }
    }

    public PictureDao getDatabase() {
        return database.getDao();
    }
}