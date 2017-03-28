package com.pifss.patient.utils;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by SMARTANS on 3/27/2017.
 */

public class myapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("hospital.db")
                .addModelClass(Hospital.class)
                .create();
        ActiveAndroid.initialize(dbConfiguration);
    }

}
