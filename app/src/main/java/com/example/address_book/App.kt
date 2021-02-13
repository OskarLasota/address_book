package com.example.address_book

import android.app.Activity
import android.app.Application
import com.example.address_book.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var injector : DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = injector


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }


}