package com.example.searchapp.dagger

import com.example.searchapp.searchrepository.SearchApi
import com.example.searchapp.searchrepository.SearchRepository
import com.example.searchapp.searchrepository.SearchStoreRemote
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