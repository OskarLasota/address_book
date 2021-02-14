package com.example.address_book.functionalities.addresslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.address_book.R
import com.example.address_book.common.translateContactsToNames
import com.example.address_book.data.Contact
import com.example.address_book.databinding.FragmentAddressBookBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressBookFragment : Fragment(R.layout.fragment_address_book) {

    private val viewModel by viewModels<AddressListViewModel>()

    private lateinit var contactAdapter: ArrayAdapter<String>

    private lateinit var binding: FragmentAddressBookBinding

    private lateinit var addressList: List<Contact>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_address_book, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getContacts()
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.addresslistRecycler.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(
                R.id.action_addressBookFragment_to_addressFragment,
                bundleOf("id" to addressList[position].id)
            )
        }
    }

    private fun setObservers() {
        viewModel.contacts.observe(viewLifecycleOwner, {
            it?.let { list ->
                addressList = list
                contactAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    list.translateContactsToNames()
                )
                binding.addresslistRecycler.apply {
                    adapter = contactAdapter
                }
            }
        })
    }

}