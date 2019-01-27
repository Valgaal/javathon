package com.example.nikita.javathon;

import android.app.Application;

import com.example.nikita.javathon.Data.Repository;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.getInstance(getApplicationContext());
    }
}
