package com.example.address_book.di

import androidx.lifecycle.ViewModel
import com.example.address_book.functionalities.addresslist.AddressListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddressListViewModel::class)
    abstract fun bindHomeViewModel(addressViewModel: AddressListViewModel): ViewModel


}