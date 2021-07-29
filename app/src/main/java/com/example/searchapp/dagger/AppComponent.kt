package com.example.searchapp.dagger

import com.example.searchapp.SearchFragment
import dagger.Component

@Component(modules = [SearchPhotoModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(searchFragment: SearchFragment)
}

