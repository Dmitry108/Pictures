package ru.bdim.pictures.detail.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface DetailView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setPicture(int id);
}
