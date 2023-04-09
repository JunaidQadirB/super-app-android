package com.moonbear.carmarz.codeclasses;

import static com.moonbear.carmarz.codeclasses.ImagePipelineConfigUtils.getDefaultImagePipelineConfig;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.FirebaseApp;
import com.moonbear.carmarz.activitiesandfragment.CustomErrorActivity;
import com.moonbear.carmarz.R;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;
import io.paperdb.Paper;

public class Carmarz extends Application {
    private static final String TAG = "CarmarzApp";
    private static Context context;

    public static Context getAppContext() {
        return Carmarz.context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();

        Carmarz.context = getApplicationContext();
        Fresco.initialize(this, getDefaultImagePipelineConfig(this));
        FirebaseApp.initializeApp(this);
        Paper.init(this);
        MyPreferences.mPrefs = getSharedPreferences(MyPreferences.prefName, MODE_PRIVATE);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true) //default: true
                .showErrorDetails(true) //default: true
                .showRestartButton(true) //default: true
                .logErrorOnRestart(true) //default: true
                .trackActivities(true) //default: false
                .minTimeBetweenCrashesMs(2000) //default: 3000
                .errorDrawable(R.drawable.imagepreview) //default: bug image
                .restartActivity(CustomErrorActivity.class) //default: null (your app's launch activity)
                .errorActivity(CustomErrorActivity.class) //default: null (default error activity)
                .eventListener(new CustomEventListener()) //default: null
                .apply();

    }

    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        @Override
        public void onLaunchErrorActivity() {
            Log.i(TAG, "onLaunchErrorActivity()");
        }

        @Override
        public void onRestartAppFromErrorActivity() {
            Log.i(TAG, "onRestartAppFromErrorActivity()");
        }

        @Override
        public void onCloseAppFromErrorActivity() {
            Log.i(TAG, "onCloseAppFromErrorActivity()");
        }
    }

}

