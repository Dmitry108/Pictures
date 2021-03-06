package ru.bdim.pictures.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.bdim.pictures.detail.presenter.DetailPresenter;
import ru.bdim.pictures.main.presenter.MainPresenter;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainPresenter presenter);
    void inject(DetailPresenter presenter);
}
