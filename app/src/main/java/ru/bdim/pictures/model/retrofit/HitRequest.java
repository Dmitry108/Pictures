package ru.bdim.pictures.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import ru.bdim.pictures.model.retrofit.Hit;

public class HitRequest {

    @Expose
    @SerializedName("hits")
    private List<Hit> hits;

    public List<String> getHits(){
        List<String> list = new ArrayList<>();
        for (Hit hit: hits){
            list.add(hit.getURL());
        }
        return list;
    }
}
