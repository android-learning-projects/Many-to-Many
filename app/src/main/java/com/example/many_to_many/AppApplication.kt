package com.example.many_to_many

import android.app.Application
import com.example.many_to_many.room.AppDatabase
import com.example.many_to_many.ui.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class AppApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database.dao()) }

    override fun onCreate() {
        super.onCreate()
    }
}