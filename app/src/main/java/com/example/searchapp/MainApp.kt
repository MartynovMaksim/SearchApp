package com.example.searchapp

import android.app.Application
import com.example.searchapp.dagger.AppComponent
import com.example.searchapp.dagger.DaggerAppComponent

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
    }
}