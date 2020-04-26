package ru.bdim.pictures.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicturesRequester {


    public static Observable<HitRequest> loadPictures (){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Pixabay pixabay = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Pixabay.class);
        return pixabay.loadPictures(PixabayApiKey.API_KEY).subscribeOn(Schedulers.io());
    }
}