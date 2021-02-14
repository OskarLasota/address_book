package com.example.address_book.functionalities.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.address_book.R
import com.example.address_book.databinding.FragmentAddressBinding
import com.example.address_book.databinding.FragmentAddressBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment(R.layout.fragment_address) {

    private lateinit var binding: FragmentAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.add_address).isVisible = false
    }
}