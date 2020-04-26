package ru.bdim.pictures.main.presenter;

import ru.bdim.pictures.main.view.IPictureViewHolder;

public interface IPictureRecyclerPresenter {

    void bindViewHolder(IPictureViewHolder holder);
    int getItemCount();
}