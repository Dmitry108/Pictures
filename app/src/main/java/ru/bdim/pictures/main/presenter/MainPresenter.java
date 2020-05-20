package ru.bdim.pictures.main.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.main.view.IPictureViewHolder;
import ru.bdim.pictures.main.view.MainView;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.preferences.DatePreference;
import ru.bdim.pictures.model.retrofit.HitRequest;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

import static ru.bdim.pictures.model.Const.TAG;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    PicturesRequester retrofit;
    @Inject
    DatePreference preferences;
    @Inject
    PictureDatabase database;
    @Inject
    DataInfo data;

    private PictureRecyclerPresenter recyclerPresenter;

    public MainPresenter(){
        recyclerPresenter = new PictureRecyclerPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        PictureApp.getComponent().inject(this);
        getPictures();
    }

    private void getPictures() {
        Date date = new Date();
        if (!date.toString().equals(preferences.getSavedDate())){
            preferences.saveDate(date);
            Log.d(TAG, "Скачивание из интернета");
            loadFromInternet();

        } else {
            Log.d(TAG, "Скачивание из базы данных");
            loadFromDatabase();
        }
    }
    public void loadFromInternet() {
        Observable<HitRequest> observable = retrofit.loadPictures();
        Disposable d = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(request -> {
                    setPictures(request.getHits());
                    updateDatabase();
                    getViewState().notifyNewPictures();
                }, throwable -> {
                    Log.d(TAG, throwable.toString());
                });
    }

    public void setPictures(List<String> list) {
        data.setPictures(list);
        data.initArray();
    }

    public void loadFromDatabase() {
        Observable<List<PictureEntity>> observable = database.getDao().getAll();
        Disposable d = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(pics ->{
                            List<String> list = new ArrayList<>();
                            for (PictureEntity pic: pics){
                                list.add(pic.getUrl());
                            }
                            setPictures(list);
                            getViewState().notifyNewPictures();
                        }, throwable -> {
                            Log.d(TAG, throwable.toString());
                            loadFromInternet();}
                );
    }

    private void updateDatabase() {
        Disposable d = database.getDao().deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    Log.d(TAG, "Удалены записи с id " + list);
                    insertToDatabase();
                }, e -> Log.d(TAG, e.toString()));
    }

    private void insertToDatabase() {
        List<PictureEntity> entities = new ArrayList<>();
        for (String url: data.getPictures()){
            entities.add(new PictureEntity(url));
        }
        Disposable d = database.getDao().addAll(entities)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(id -> Log.d(TAG, "Добавлены записи с id " + id),
                                e -> Log.d(TAG, e.toString()));
    }

    public void setChoice(final int position) {
        int f = data.setCurrentChoice(position);
        Log.d(TAG, String.format("№%d - %d", position, f));
        getViewState().showPicture();
    }

    public PictureRecyclerPresenter getPictureRecyclerPresenter(){
        return recyclerPresenter;
    }

    private class PictureRecyclerPresenter implements IPictureRecyclerPresenter {

        @Override
        public void bindViewHolder(IPictureViewHolder holder) {
            holder.setImage(data.getPictures().get(holder.getIndex()));
        }
        @Override
        public int getItemCount() {
            if (data.getPictures() == null){
                return 0;
            } else {
                return data.getPictures().size();
            }
        }
    }
}