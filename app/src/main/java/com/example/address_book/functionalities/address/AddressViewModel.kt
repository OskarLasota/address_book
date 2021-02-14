package com.example.address_book.functionalities.address

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.address_book.common.AppScheduler
import com.example.address_book.data.Contact
import com.example.address_book.data.repository.AddressRepository
import io.reactivex.disposables.CompositeDisposable

class AddressViewModel @ViewModelInject constructor(
    private val repo: AddressRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler : AppScheduler
) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()
    val contact : LiveData<Contact> = _contact

    private val _error = MutableLiveData<String>()
    private val error : LiveData<String> = _error

    fun getContact(id : Int){
        compositeDisposable.add(
            repo.getContact(id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({
                    _contact.postValue(it)
                }, {
                    _error.postValue(it.toString())
                })
        )
    }

}