package com.example.address_book.di

import com.example.address_book.ui.AddressBookFragment
import com.example.address_book.ui.AddressFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAdressBookFragment(): AddressBookFragment

    @ContributesAndroidInjector
    abstract fun contributeAdressFragment(): AddressFragment


}