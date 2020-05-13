package ru.bdim.pictures.detail.presenter;

import android.util.Log;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.detail.view.DetailView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.Model;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    @Inject
    Model model;

    public DetailPresenter(){
        PictureApp.getComponent().inject(this);
    }

    public void setPicture(){
        int index = model.getCurrentPosition();
        Log.d(Const.TAG, String.valueOf(index));
        getViewState().setPicture(model.getPicture());
    }
}