package com.example.many_to_many

import android.app.Application
import com.example.many_to_many.data.room.AppDatabase
import com.example.many_to_many.ui.Repository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber


@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}