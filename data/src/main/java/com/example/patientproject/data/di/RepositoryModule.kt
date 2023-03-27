package com.example.patientproject.data.di

import com.example.patientproject.data.datasource.PatientsDataSource
import com.example.patientproject.data.repository.PatientsRepositoryImpl
import com.example.patientproject.domain.repository.PatientsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(patientsDataSource: PatientsDataSource): PatientsRepository {
        return PatientsRepositoryImpl(patientsDataSource)
    }
}