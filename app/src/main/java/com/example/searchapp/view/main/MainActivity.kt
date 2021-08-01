package com.example.searchapp.view.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchapp.Application
import com.example.searchapp.R
import com.example.searchapp.dagger.component.AppComponent
import com.example.searchapp.view.search.PhotoFragment
import com.example.searchapp.view.search.SearchFragment

// Непонятно, как работает
val Context.appComponent: AppComponent
    get() = when (this) {
        is Application -> appComponent
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

    override fun openPhotoFragment(photoUrl: String) {
        val fragment = PhotoFragment.newInstance(photoUrl)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment).commit()
    }


}