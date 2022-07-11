package com.geektech.newsapp

import android.app.Application
import androidx.room.Room
import com.geektech.newsapp.room.AppDatabase

class App : Application() {
    companion object{
        lateinit var database: AppDatabase

    }
    fun getInstanc(): AppDatabase {
        return database
    }

    fun getDatabase():AppDatabase{
        return database
    }

    override fun onCreate() {
        super.onCreate()

        database =
            Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .allowMainThreadQueries()
                .build()
    }
}