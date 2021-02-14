package com.example.address_book.functionalities.newaddress

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _inserted = MutableLiveData<Boolean>()
    val inserted : LiveData<Boolean> = _inserted

    private val _error = MutableLiveData<String>()
    private val error : LiveData<String> = _error

    fun addContact(contact: Contact) {
        compositeDisposable.add(
            repo.insertContact(contact)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({
                    _inserted.postValue(true)
                },{
                    _inserted.postValue(false)
                    _error.postValue(it.toString())
                })
        )
    }


}