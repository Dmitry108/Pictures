package ru.bdim.pictures.main.presenter;

import android.util.Log;

import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.detail.application.PictureApp;
import ru.bdim.pictures.main.view.MainView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.Model;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;

    public MainPresenter(){
        model = PictureApp.getInstance().getModel();
    }

    public void setChoice(final int position) {
        int f = model.increase(position);
        Log.d(Const.TAG, String.format("№%d - %d", position, f));
        getViewState().showPicture();
    }

    public List<Integer> getPicturesList() {
        return model.getPicturesList();
    }
}