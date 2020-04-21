package ru.bdim.pictures.source;

import java.util.ArrayList;
import java.util.List;

import ru.bdim.pictures.R;

public class PictureSource {
    List<Integer> pictures;

    public PictureSource(){
        pictures = new ArrayList<>();
        pictures.add(R.drawable.cat1);
        pictures.add(R.drawable.cat2);
        pictures.add(R.drawable.cat3);
        pictures.add(R.drawable.cat4);
        pictures.add(R.drawable.cat5);
        pictures.add(R.drawable.cat6);
    }
    public List<Integer> getPicturesList(){
        return pictures;
    }
}
