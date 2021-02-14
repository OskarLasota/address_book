package com.example.address_book.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.address_book.data.Contact
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact) : Completable

    @Query("SELECT * FROM contact_table ORDER BY id ASC")
    fun getContacts() : Single<List<Contact>>

    @Query("SELECT * FROM contact_table WHERE id = :id")
    fun getContact(id : Int) : Single<Contact>

}