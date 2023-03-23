package com.example.patientproject.data.di

import com.example.patientproject.data.datasource.PatientsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL ="https://patients-app-api.herokuapp.com/"


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePatientsDataSource(retrofit: Retrofit): PatientsDataSource {
        return retrofit.create(PatientsDataSource::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideRepository(patientsDataSource: PatientsDataSource):PatientsRepository{
//        return PatientsRepository(patientsDataSource)
//    }
}