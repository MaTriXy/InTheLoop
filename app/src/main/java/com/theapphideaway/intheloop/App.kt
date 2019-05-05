package com.theapphideaway.intheloop

import android.app.Application
import android.support.v7.app.AppCompatDelegate

public class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}