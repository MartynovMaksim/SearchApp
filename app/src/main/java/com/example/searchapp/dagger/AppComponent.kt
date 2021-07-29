package com.example.searchapp.dagger

import com.example.searchapp.AppModule
import com.example.searchapp.presenter.SearchViewModel
import dagger.Component

@Component(modules = [AppModule::class, SearchPhotoModule::class])
interface AppComponent {
    fun inject(viewModel: SearchViewModel)
}

