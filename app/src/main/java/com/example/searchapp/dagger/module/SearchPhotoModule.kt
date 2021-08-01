package com.example.searchapp.dagger.module

import com.example.searchapp.model.search.SearchApi
import com.example.searchapp.model.search.SearchRepository
import com.example.searchapp.model.search.SearchStoreRemote
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class SearchPhotoModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    fun provideSearchStoreRemote(searchApi: SearchApi): SearchStoreRemote {
        return SearchStoreRemote(searchApi)
    }

    @Provides
    fun provideSearchRepository(searchStoreRemote: SearchStoreRemote) : SearchRepository {
        return SearchRepository(searchStoreRemote)
    }
}