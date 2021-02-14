package com.example.address_book.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fname: String,
    val lname: String,
    val email: String,
    val phoneNumber: String,
    val address1: String
)


