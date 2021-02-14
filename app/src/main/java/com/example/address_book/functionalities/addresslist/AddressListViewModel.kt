package com.example.address_book.functionalities.addresslist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.address_book.common.scheduler.AppScheduler
import com.example.address_book.data.Contact
import com.example.address_book.data.repository.AddressRepository
import io.reactivex.disposables.CompositeDisposable

class AddressListViewModel @ViewModelInject constructor(
    private val repo: AddressRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler : AppScheduler
) : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts : LiveData<List<Contact>> = _contacts

    private val _filtered = MutableLiveData<List<Contact>>()
    val filtered : LiveData<List<Contact>> = _filtered

    private val _error = MutableLiveData<String>()
    private val error : LiveData<String> = _error

    fun getContacts() {
        compositeDisposable.add(
            repo.getContacts()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.mainThread())
                .subscribe({
                    _contacts.postValue(it)
                }, {
                    _error.postValue(it.toString())
                })
        )
    }

    fun filterContacts(query : String){
        var result = arrayListOf<Contact>()
        _contacts.value?.map {
            if(it.fname.toLowerCase().contains(query.toLowerCase()) || it.lname.toLowerCase().contains(query.toLowerCase())){
                result.add(it)
            }
        }
        _filtered.postValue(result)
    }


}