package ru.bdim.pictures.model.data;

import java.util.ArrayList;
import java.util.List;

public class DataInfo {

    private List<String> pictures;
    private List<Integer> frequencyList;
    private int currentPosition;

    public DataInfo(){
        pictures = new ArrayList<>();
        frequencyList = new ArrayList<>();
    }

    public void setPictures(List<String> list){
        pictures = list;
    }
    public List<String> getPictures(){
        return pictures;
    }
    public void initArray() {
        for (int i = 0; i< pictures.size(); i++){
            frequencyList.add(0);
        }
    }
    public int setCurrentChoice (int position) {
        currentPosition = position;
        frequencyList.set(position, frequencyList.get(position) + 1);
        return frequencyList.get(position);
    }

    public int getCurrentPosition(){
        return currentPosition;
    }
}