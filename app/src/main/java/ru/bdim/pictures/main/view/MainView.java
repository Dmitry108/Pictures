package ru.bdim.pictures.main.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showPicture();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void notifyNewPictures();
}