package co.leinaro.gallera;


import android.app.Application;

public class GalletaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Storage.initializeStorage(this);

    }
}
