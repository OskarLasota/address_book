package com.example.address_book.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fname: String,
    val lname: String,
    val email: String,
    val phoneNumber: String,
    val address1: String
)