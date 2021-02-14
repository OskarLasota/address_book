package com.example.address_book.functionalities.addresslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.address_book.R
import com.example.address_book.functionalities.newaddress.NewAddressViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressBookFragment : Fragment(R.layout.fragment_address_book) {

    private val viewModel by viewModels<AddressListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getContacts()
    }

}