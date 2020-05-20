package ru.bdim.pictures.app;

import javax.inject.Singleton;

import dagger.Component;
import ru.bdim.pictures.detail.presenter.DetailPresenter;
import ru.bdim.pictures.main.presenter.MainPresenter;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
    void inject(MainPresenter presenter);
    void inject(DetailPresenter presenter);
}
