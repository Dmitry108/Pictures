package ru.bdim.pictures.detail.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import ru.bdim.pictures.R;
import ru.bdim.pictures.detail.presenter.DetailPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailView {

    @InjectPresenter
    DetailPresenter presenter;
    @BindView(R.id.img_picture)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        presenter.setPicture();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void setPicture(String url) {
        Picasso.with(this).load(url).into(image);
    }
}