package ru.bdim.pictures.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.fabric.sdk.android.Fabric;


public class PictureApp extends Application {

    private static PictureApp instance;
    private static AppComponent component;

    private static RefWatcher refWatcher;

    public static PictureApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = createComponent();

        setUpCrashlytics();
        setUpLeakCanary();
    }

    private void setUpLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void setUpCrashlytics() {
        Fabric.with(this, new Crashlytics());
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static AppComponent getComponent(){
        return component;
    }

    private static AppComponent createComponent(){
        return DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    @Override
    public void onTerminate() {
        instance = null;
        super.onTerminate();
    }
}