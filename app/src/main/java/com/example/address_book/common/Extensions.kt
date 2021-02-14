package com.example.address_book.common

import com.example.address_book.data.Contact

fun List<Contact>.translateContactsToNames() : ArrayList<String>{
    return this.map {
        it.fname + " " + it.lname
    } as ArrayList<String>
}