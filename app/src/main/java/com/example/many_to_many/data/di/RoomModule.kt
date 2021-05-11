package com.bosco.mrroom.di

import android.content.Context
import androidx.room.Room
import com.example.many_to_many.data.room.AppDatabase
import com.example.many_to_many.data.room.dao.SongsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * @author bsoft-61 on 15/2/21.
 * */
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDAO(database: AppDatabase): SongsDao {
        return database.dao()
    }


}