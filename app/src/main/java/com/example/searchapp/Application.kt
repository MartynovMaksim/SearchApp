package com.example.searchapp

import android.app.Application
import com.example.searchapp.dagger.component.AppComponent
import com.example.searchapp.dagger.component.DaggerAppComponent

class Application : Application() {

    companion object {
        lateinit var instance: com.example.searchapp.Application
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.create()
    }
}