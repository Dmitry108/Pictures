package ru.bdim.pictures.application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.preferences.DatePreference;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

@Module
public class AppModule {

    @Singleton
    @Provides
    PictureDatabase getDatabase(){
        return Room.databaseBuilder(PictureApp.getInstance(), PictureDatabase.class, "pictures")
                .build();
    }

    @Singleton
    @Provides
    DatePreference getPreference(){
        return new DatePreference(PictureApp.getInstance());
    }

    @Singleton
    @Provides
    DataInfo getDataInfo(){
        return new DataInfo();
    }

    @Singleton
    @Provides
    PicturesRequester internet() {
        return new PicturesRequester();
    }
}
