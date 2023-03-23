package com.example.patientproject.data.di

import com.example.patientproject.data.datasource.PatientsDataSource
import com.example.patientproject.data.repository.PatientsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(patientsDataSource: PatientsDataSource):PatientsRepositoryImpl{
        return PatientsRepositoryImpl(patientsDataSource)
    }
}