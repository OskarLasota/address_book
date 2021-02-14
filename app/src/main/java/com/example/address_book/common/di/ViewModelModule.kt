package com.example.address_book.common.di

import androidx.lifecycle.ViewModel
import com.example.address_book.functionalities.addresslist.AddressListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
@InstallIn(ApplicationComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddressListViewModel::class)
    abstract fun bindHomeViewModel(addressViewModel: AddressListViewModel): ViewModel


}