package ru.bdim.pictures.model.data;

import java.util.ArrayList;
import java.util.List;

public class DataInfo {

    private String pictureUrl;
    private List<Integer> frequencyList;
    private int currentPosition;

    public DataInfo(){
        frequencyList = new ArrayList<>();
    }

    public void initArray(int count) {
        for (int i = 0; i< count; i++){
            frequencyList.add(0);
        }
    }

    public List<Integer> getArray(){
        return frequencyList;
    }

    public int setCurrentChoice (int position, String url) {
        currentPosition = position;
        pictureUrl = url;
        frequencyList.set(position, frequencyList.get(position) + 1);
        return frequencyList.get(position);
    }

    public String getPicture(){
        return pictureUrl;
    }

    public int getCurrentPosition(){
        return currentPosition;
    }
}