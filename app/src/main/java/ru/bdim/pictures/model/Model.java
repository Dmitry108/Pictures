package ru.bdim.pictures.model;

import android.util.Log;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

import io.reactivex.Single;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDao;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.preferences.DatePreference;
import ru.bdim.pictures.model.retrofit.HitRequest;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

public class Model {

    private Date date;

    //части модели
    private DataInfo dataInfo;
    private PicturesRequester internet;
    private PictureDatabase database;
    private DatePreference preferences;

    public Model(){
        dataInfo = new DataInfo();
        internet = new PicturesRequester();
        database = PictureApp.getDatabase();
        preferences = PictureApp.getPreferences();
        date = new Date();

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
        if (!date.toString().equals(preferences.getSavedDate())){
           preferences.saveDate(date);
           return true;
        } else {
            return false;
        }
    }

    public List<Integer> getArray() {
        return dataInfo.getArray();
    }

    public PictureDao getDatabase() {
        return PictureApp.getDatabase().getDao();
    }
}