package com.example.searchapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchapp.dagger.AppComponent

// Непонятно, как работает
val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApp -> appComponent
        else -> this.applicationContext.appComponent
    }

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openSearchFragment()
    }

    override fun openSearchFragment() {
        val fragment = SearchFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment).commit()
    }


}