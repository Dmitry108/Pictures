package ru.bdim.pictures.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PicturesRequester {

    private Pixabay pixabay;

    public PicturesRequester(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        pixabay = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Pixabay.class);
    }
    public Observable<HitRequest> loadPictures (){
        return pixabay.loadPictures(PixabayApiKey.API_KEY).subscribeOn(Schedulers.io());
    }
}