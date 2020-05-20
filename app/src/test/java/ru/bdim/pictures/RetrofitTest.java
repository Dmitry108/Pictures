package ru.bdim.pictures;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;
import ru.bdim.pictures.app.DaggerTestComponent;
import ru.bdim.pictures.app.TestComponent;
import ru.bdim.pictures.app.TestModule;
import ru.bdim.pictures.main.presenter.MainPresenter;
import ru.bdim.pictures.model.data.DataInfo;
import ru.bdim.pictures.model.database.PictureDao;
import ru.bdim.pictures.model.database.PictureDatabase;
import ru.bdim.pictures.model.database.PictureEntity;
import ru.bdim.pictures.model.retrofit.HitRequest;
import ru.bdim.pictures.model.retrofit.PicturesRequester;

@RunWith(MockitoJUnitRunner.class)
public class RetrofitTest {

    private MainPresenter mainPresenter;
    private DataInfo data;

    @BeforeClass
    public static void setupClass(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(xx ->
                Schedulers.trampoline());
    }

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        mainPresenter = Mockito.spy(MainPresenter.class);
        initTestComponent();
    }

    private void initTestComponent() {
        TestComponent component =
                DaggerTestComponent.builder()
                        .testModule(new TestModule(){

                            private List<String> list = Arrays.asList(new String[]{"111", "222", "333"});

                            @Override
                            public PicturesRequester retrofit(){
                                PicturesRequester requester = super.retrofit();
                                HitRequest hits = new HitRequest();
                                hits.setHits(list);
                                Mockito.when(requester.loadPictures()).thenReturn(Observable.just(hits));
                                return requester;
                            }
                            @Override
                            public PictureDatabase provideDatabase(){
                                PictureDatabase database = super.provideDatabase();
//                        List<PictureEntity> entities = new ArrayList<>();
//                        for (String pic: list){
//                            entities.add(new PictureEntity(pic));
//                        }
//                      Mockito.when(database.getAll()).thenReturn(Observable.just(entities));
                                return database;
                            }
                            @Override
                            public DataInfo getDataInfo(){
                                data = super.getDataInfo();
                                return data;
                            }
                        }).build();
        component.inject(mainPresenter);
    }

    @Test
    public void testRetrofit(){
        List<String> list = Arrays.asList(new String[]{"111", "222", "333"});
        mainPresenter.loadFromInternet();
        Mockito.verify(data).setPictures(list);
    }

//    @Test
//    public void testDatabase(){
//        List<String> list = Arrays.asList(new String[]{"111", "222", "333"});
//        mainPresenter.loadFromDatabase();
//        Mockito.verify(data).setPictures(list);
//    }
}