package com.world.flobiz_assgnment.di

import com.world.flobiz_assgnment.network.Destination
import com.world.flobiz_assgnment.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ListModule {

    @Singleton
    @Provides
    fun provideListData(
        destination: Destination
    ) : ListRepository {
        return ListRepository(destination)
    }

}