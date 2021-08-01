package com.example.searchapp.dagger.component

import com.example.searchapp.dagger.module.SearchPhotoModule
import com.example.searchapp.dagger.module.ViewModelModule
import com.example.searchapp.view.search.SearchFragment
import dagger.Component

@Component(modules = [SearchPhotoModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(searchFragment: SearchFragment)
}

