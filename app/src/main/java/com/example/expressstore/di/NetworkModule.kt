package com.example.expressstore.di

import com.example.expressstore.services.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "http://10.100.100.226:9090"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }


}