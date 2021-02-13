package com.example.address_book.di

import androidx.lifecycle.ViewModel
import com.example.address_book.MainActivity
import com.example.address_book.functionalities.addresslist.AddressListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap


@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddressListViewModel::class)
    abstract fun bindHomeViewModel(addressViewModel: AddressListViewModel): ViewModel


}