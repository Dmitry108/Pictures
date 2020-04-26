package ru.bdim.pictures.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Pixabay {
    @GET("api/")
    Observable<HitRequest> loadPictures(@Query("key") String apiKey);
}