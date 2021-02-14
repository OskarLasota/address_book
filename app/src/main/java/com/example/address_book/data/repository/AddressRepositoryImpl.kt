package com.example.address_book.data.repository

import android.app.Application
import com.example.address_book.data.Contact
import com.example.address_book.data.db.ContactDao
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(private val dao : ContactDao) : AddressRepository {

    override fun getContacts(): List<Contact> {
        return emptyList()
    }

    override fun insertContact(contact: Contact) {

    }
}