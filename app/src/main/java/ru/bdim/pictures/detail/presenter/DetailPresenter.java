package ru.bdim.pictures.detail.presenter;

import android.util.Log;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.detail.view.DetailView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.data.DataInfo;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    DataInfo data;

    public DetailPresenter(){
        PictureApp.getComponent().inject(this);
    }

    public void setPicture(){
        int index = data.getCurrentPosition();
        Log.d(Const.TAG, String.valueOf(index));
        getViewState().setPicture(data.getPictures().get(index));
    }
}