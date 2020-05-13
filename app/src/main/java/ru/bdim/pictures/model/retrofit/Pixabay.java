package ru.bdim.pictures.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.bdim.pictures.model.retrofit.HitRequest;

public interface Pixabay {
    @GET("api/")
    Observable<HitRequest> loadPictures(@Query("key") String apiKey);
}