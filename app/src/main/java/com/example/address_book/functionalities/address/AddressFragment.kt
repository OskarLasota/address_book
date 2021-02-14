package com.example.address_book.functionalities.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.address_book.R
import com.example.address_book.data.Contact
import com.example.address_book.databinding.FragmentAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : Fragment(R.layout.fragment_address) {

    private lateinit var binding: FragmentAddressBinding
    private val viewModel by viewModels<AddressViewModel>()

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
        setObservers()
        if (arguments?.getInt("id") != null) {
            viewModel.getContact(arguments?.getInt("id") as Int)
        }
    }

    private fun setObservers() {
        viewModel.contact.observe(viewLifecycleOwner, {
            initializeUI(it)
        })
    }

    private fun initializeUI(contact: Contact) {
        binding.tvFname.text = contact.fname
        binding.tvLname.text = contact.lname
        binding.tvAddress.text = contact.address1
        binding.tvPhone.text = contact.phoneNumber
        binding.tvEmail.text = contact.email
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.add_address).isVisible = false
    }
}