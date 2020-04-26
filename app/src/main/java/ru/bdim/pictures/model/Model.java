package ru.bdim.pictures.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class Model {

    private String pictureUrl;
    private List<Integer> frequencyList;
    private int currentPosition;

    public Model(){
        frequencyList = new ArrayList<>();
    }

    public void initArray(int count) {
        for (int i = 0; i< count; i++){
            frequencyList.add(0);
        }
    }

    public int setCurrentChoice (int position, String url) {
        currentPosition = position;
        pictureUrl = url;
        frequencyList.set(position, frequencyList.get(position) + 1);
        return frequencyList.get(position);
    }

    public Single<String> getPicture(){
        return Single.create((SingleOnSubscribe<String>) emitter ->
                emitter.onSuccess(pictureUrl)
        ).subscribeOn(Schedulers.io());
    }

    public Observable<HitRequest> loadPictures(){
        return PicturesRequester.loadPictures();
    }

    public int getCurrentPosition(){
        return currentPosition;
    }
}