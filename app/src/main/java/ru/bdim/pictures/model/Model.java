package ru.bdim.pictures.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import ru.bdim.pictures.source.PictureSource;

public class Model {

    private List<Integer> pictures;
    private List<Integer> frequencyList;
    private int currentPosition;

    public Model(){
        PictureSource source = new PictureSource();
        pictures = source.getPicturesList();
        frequencyList = new ArrayList<>();
        initArray(pictures.size());
    }

    private void initArray(int count) {
        for (int i = 0; i< count; i++){
            frequencyList.add(0);
        }
    }

    public int increase(int position) {
        currentPosition = position;
        frequencyList.set(position, frequencyList.get(position) + 1);
        return frequencyList.get(position);
    }
    public Single<Integer> getPicture(final int index){
        return Single.create((SingleOnSubscribe<Integer>) emitter ->
                emitter.onSuccess(pictures.get(index))
        ).subscribeOn(Schedulers.io());
    }
    public List<Integer> getPicturesList() {
        return pictures;
    }
    public int getCurrentPosition(){
        return currentPosition;
    }
}