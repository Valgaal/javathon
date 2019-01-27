package com.example.nikita.javathon;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repository.getInstance(getApplicationContext());
    }
}
