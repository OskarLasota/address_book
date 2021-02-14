package com.example.address_book.data.repository

import com.example.address_book.data.Contact

interface AddressRepository {

    fun getContacts() : List<Contact>
    fun insertContact(contact: Contact)

}