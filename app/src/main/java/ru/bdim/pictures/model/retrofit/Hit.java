package ru.bdim.pictures.model.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {
    @Expose
    @SerializedName("webformatURL")
    private String webformatURL;

    public Hit(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getURL(){
        return webformatURL;
    }
}
