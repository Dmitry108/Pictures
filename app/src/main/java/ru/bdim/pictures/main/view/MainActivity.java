package ru.bdim.pictures.main.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bdim.pictures.application.PictureApp;
import ru.bdim.pictures.detail.view.DetailActivity;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.bdim.pictures.R;
import ru.bdim.pictures.main.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.rcw_viewer)
    RecyclerView recycler;

    private PicturesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initRecycler();
        RefWatcher refWatcher = PictureApp.getRefWatcher();
        refWatcher.watch(this);
    }
    private void initRecycler() {
        adapter = new PicturesRecyclerAdapter(presenter.getPictureRecyclerPresenter());
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(manager);
        adapter.setListener(position -> presenter.setChoice(position));
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new PicturesItemDecoration(50));
    }
    @Override
    public void showPicture() {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void notifyNewPictures() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}