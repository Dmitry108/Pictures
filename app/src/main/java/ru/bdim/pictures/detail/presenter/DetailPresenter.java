package ru.bdim.pictures.detail.presenter;

import android.util.Log;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.detail.view.DetailView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.Model;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private Model model;

    public DetailPresenter(){
        model = PictureApp.getModel();
    }

    public void setPicture(){
        int index = model.getCurrentPosition();
        Log.d(Const.TAG, String.valueOf(index));
        getViewState().setPicture(model.getPicture());
    }
}