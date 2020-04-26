package ru.bdim.pictures.main.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.bdim.pictures.detail.application.PictureApp;
import ru.bdim.pictures.main.view.IPictureViewHolder;
import ru.bdim.pictures.main.view.MainView;
import ru.bdim.pictures.model.Const;
import ru.bdim.pictures.model.Hit;
import ru.bdim.pictures.model.HitRequest;
import ru.bdim.pictures.model.Model;
import ru.bdim.pictures.model.PicturesRequester;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;
    private List<Hit> pictures;
    private PictureRecyclerPresenter recyclerPresenter;

    public MainPresenter(){
        model = PictureApp.getInstance().getModel();

        recyclerPresenter = new PictureRecyclerPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        getPictures();
    }

    private void getPictures() {
        Observable<HitRequest> observable = PicturesRequester.loadPictures();
        Disposable d = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(request -> {
                    pictures = request.getHits();
                    model.initArray(pictures.size());
                    getViewState().notifyNewPictures();
                }, throwable -> {
                    Log.d(Const.TAG, throwable.toString());
        });
    }

    public void setChoice(final int position) {
        int f = model.setCurrentChoice(position, pictures.get(position).getURL());
        Log.d(Const.TAG, String.format("№%d - %d", position, f));
        getViewState().showPicture();
    }

    public PictureRecyclerPresenter getPictureRecyclerPresenter(){
        return recyclerPresenter;
    }

    private class PictureRecyclerPresenter implements IPictureRecyclerPresenter {

        @Override
        public void bindViewHolder(IPictureViewHolder holder) {
            holder.setImage(pictures.get(holder.getIndex()).getURL());
        }
        @Override
        public int getItemCount() {
            if (pictures == null){
                return 0;
            } else {
                return pictures.size();
            }

        }
    }
}