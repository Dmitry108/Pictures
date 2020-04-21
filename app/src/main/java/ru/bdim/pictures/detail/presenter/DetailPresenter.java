package ru.bdim.pictures.detail.presenter;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.detail.view.DetailView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.Model;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    private Model model;

    public DetailPresenter(){
        model = Model.getInstance();
    }

    public void setPicture(){
        int index = model.getCurrentPosition();
        Log.d(Const.TAG, String.valueOf(index));
        Disposable d = model.getPicture(model.getCurrentPosition())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> getViewState().setPicture(id));
    }
}