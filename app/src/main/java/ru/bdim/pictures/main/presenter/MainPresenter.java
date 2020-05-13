package ru.bdim.pictures.main.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.main.view.IPictureViewHolder;
import ru.bdim.pictures.main.view.MainView;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.retrofit.HitRequest;
import ru.bdim.pictures.model.Model;

import static ru.bdim.pictures.model.Const.TAG;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;
    private List<String> pictures;
    private PictureRecyclerPresenter recyclerPresenter;

    public MainPresenter(){
        model = PictureApp.getModel();

        recyclerPresenter = new PictureRecyclerPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        getPictures();
    }

    private void getPictures() {
        if (model.isFirstTimePerDay()){
            Log.d(TAG, "Скачивание из интернета");
            loadFromInternet();

        } else {
            Log.d(TAG, "Скачивание из базы данных");
            loadFromDatabase();
        }
    }

    private void loadFromInternet() {
        Observable<HitRequest> observable = model.loadFromInternet();
        Disposable d = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(request -> {
                    pictures = request.getHits();
                    model.initArray(pictures.size());
                    updateDatabase();
                    getViewState().notifyNewPictures();
                }, throwable -> {
                    Log.d(TAG, throwable.toString());
                });
    }

    private void loadFromDatabase() {
        Observable<List<PictureEntity>> observable = model.loadFromDatabase();
        Disposable d = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(list ->{
                            pictures = new ArrayList<>();
                            for (PictureEntity pic: list){
                                pictures.add(pic.getUrl());
                            }
                            model.initArray(pictures.size());
                            getViewState().notifyNewPictures();
                        }, throwable -> {
                            Log.d(TAG, throwable.toString());
                            loadFromInternet();}
                );
    }

    private void updateDatabase() {
        Disposable d = model.getDatabase().deleteAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    Log.d(TAG, "Удалены записи с id " + list);
                    insertToDatabase();
                }, e -> Log.d(TAG, e.toString()));
    }

    private void insertToDatabase() {
        List<PictureEntity> entities = new ArrayList<>();
        for (String url: pictures){
            entities.add(new PictureEntity(url));
        }
        Disposable d = model.getDatabase().addAll(entities)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(id -> Log.d(TAG, "Добавлены записи с id " + id),
                                e -> Log.d(TAG, e.toString()));
    }

    public void setChoice(final int position) {
        int f = model.setCurrentChoice(position, pictures.get(position));
        Log.d(TAG, String.format("№%d - %d", position, f));
        getViewState().showPicture();
    }

    public PictureRecyclerPresenter getPictureRecyclerPresenter(){
        return recyclerPresenter;
    }

    private class PictureRecyclerPresenter implements IPictureRecyclerPresenter {

        @Override
        public void bindViewHolder(IPictureViewHolder holder) {
            holder.setImage(pictures.get(holder.getIndex()));
        }
        @Override
        public int getItemCount() {
            if (pictures == null){
                return 0;
            } else {
                return pictures.size();
            }

        }
    }
}