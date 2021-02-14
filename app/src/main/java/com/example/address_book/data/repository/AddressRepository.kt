package com.example.address_book.data.repository

import com.example.address_book.data.Contact
import io.reactivex.Completable
import io.reactivex.Single

interface AddressRepository {

    fun getContacts() : Single<List<Contact>>
    fun insertContact(contact: Contact) : Completable
    fun getContact(id : Int) : Single<Contact>

}