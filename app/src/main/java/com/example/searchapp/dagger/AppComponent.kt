package com.example.searchapp.dagger

import com.example.searchapp.presenter.SearchViewModel
import dagger.Component

@Component(modules = [SearchPhotoModule::class])
interface AppComponent {
    fun inject(viewModel: SearchViewModel)
}

