package mottimotti.com.sandbox_v8_android;

import android.app.Application;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
//        }
    }
}
