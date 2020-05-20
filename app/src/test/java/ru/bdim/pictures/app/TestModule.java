package ru.bdim.pictures.app;

import androidx.room.Room;

import org.mockito.Mockito;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDao;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.preferences.DatePreference;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

@Module
public class TestModule {

    @Singleton
    @Provides
    public PicturesRequester retrofit() {
        return Mockito.mock(PicturesRequester.class);
    }
    @Singleton
    @Provides
    public PictureDatabase provideDatabase(){

//        PictureDao dao = new PictureDao() {
//            @Override
//            public Single<List<Long>> addAll(List<PictureEntity> pictureEntity) {
//                return null;
//            }
//            @Override
//            public Single<Integer> deleteAll() {
//                return null;
//            }
//            @Override
//            public Observable<List<PictureEntity>> getAll() {
//                return null;
//            }
//        };
        return Mockito.mock(PictureDatabase.class);//null;//dao;
    }
    @Singleton
    @Provides
    DatePreference getPreference(){
        return Mockito.mock(DatePreference.class);
    }
    @Singleton
    @Provides
    public DataInfo getDataInfo(){
        return Mockito.spy(DataInfo.class);
    }
}
