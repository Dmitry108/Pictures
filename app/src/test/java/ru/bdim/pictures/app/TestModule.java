package ru.bdim.pictures.app;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDatabase;
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
        return Mockito.mock(PictureDatabase.class);
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
