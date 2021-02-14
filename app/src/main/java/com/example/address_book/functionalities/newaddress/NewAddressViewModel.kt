package com.example.address_book.functionalities.newaddress

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.address_book.common.AppScheduler
import com.example.address_book.data.Contact
import com.example.address_book.data.repository.AddressRepository
import io.reactivex.disposables.CompositeDisposable

class NewAddressViewModel @ViewModelInject constructor(
    private val repo: AddressRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler : AppScheduler
) : ViewModel() {

    fun addContact(contact: Contact) {
        compositeDisposable.add(
            repo.insertContact(contact)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({

                },{
    
                })
        )
    }

    fun getContacts() {
        compositeDisposable.add(
            repo.getContacts()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({

                }, {

                })
        )
    }

}