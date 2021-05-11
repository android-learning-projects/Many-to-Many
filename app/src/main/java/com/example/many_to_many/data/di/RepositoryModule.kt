package com.bosco.mrroom.di

import com.example.many_to_many.data.room.dao.SongsDao
import com.example.many_to_many.ui.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 18/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
       dao: SongsDao
    ): Repository {
        return Repository(dao = dao)
    }


}