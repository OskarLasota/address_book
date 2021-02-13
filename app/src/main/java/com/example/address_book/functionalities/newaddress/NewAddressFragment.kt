package com.example.address_book.functionalities.newaddress

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.example.address_book.R
import com.example.address_book.di.Injectable

class NewAddressFragment : Fragment(R.layout.fragment_new_address), Injectable {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.add_address).isVisible = false
    }

}