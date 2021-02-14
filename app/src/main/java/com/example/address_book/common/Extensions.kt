package com.example.address_book.common

import com.example.address_book.data.Contact

//mapping the contacts list into an arraylist of contact names to show in the list view
fun List<Contact>.translateContactsToNames() : ArrayList<String>{
    return this.map {
        it.fname + " " + it.lname
    } as ArrayList<String>
}