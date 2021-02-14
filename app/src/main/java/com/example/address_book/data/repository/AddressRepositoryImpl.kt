package com.example.address_book.data.repository

import com.example.address_book.data.Contact
import com.example.address_book.data.db.ContactDao
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(private val dao : ContactDao) : AddressRepository {

    override fun getContacts(): Single<List<Contact>> {
        return Single.defer{
            return@defer dao.getContacts()
        }
    }

    override fun insertContact(contact: Contact) : Completable {
        return Completable.defer{
            return@defer dao.insert(contact)
        }
    }

    override fun getContact(id: Int): Single<Contact> {
        return Single.defer{
            return@defer dao.getContact(id)
        }
    }
}