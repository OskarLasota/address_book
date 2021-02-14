package com.example.address_book.common.di

import com.example.address_book.data.repository.AddressRepository
import com.example.address_book.data.repository.AddressRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAddressRepository(addressRepository: AddressRepositoryImpl): AddressRepository


}