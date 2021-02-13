package com.example.address_book.di

import com.example.address_book.functionalities.addresslist.AddressBookFragment
import com.example.address_book.functionalities.address.AddressFragment
import com.example.address_book.functionalities.newaddress.NewAddressFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAdressBookFragment(): AddressBookFragment

    @ContributesAndroidInjector
    abstract fun contributeAdressFragment(): AddressFragment

    @ContributesAndroidInjector
    abstract fun contributeNewAddressFragment(): NewAddressFragment


}