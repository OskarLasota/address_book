package com.example.address_book.common.di

import android.content.Context
import androidx.room.Room
import com.example.address_book.data.db.AppDatabase
import com.example.address_book.data.db.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database").build()
    }

    @Provides
    fun provideContactsDao(database: AppDatabase): ContactDao {
        return database.contactDao()
    }

}