package com.example.address_book.functionalities.newaddress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.address_book.R
import com.example.address_book.data.Contact
import com.example.address_book.databinding.FragmentAddressBinding
import com.example.address_book.databinding.FragmentNewAddressBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_address.*

@AndroidEntryPoint
class NewAddressFragment : Fragment(R.layout.fragment_new_address) {

    private val viewModel by viewModels<NewAddressViewModel>()
    private lateinit var binding: FragmentNewAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_address, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setListeners()
        setObservers()
    }

    private fun setObservers(){
        viewModel.inserted.observe(viewLifecycleOwner, {
            if(it){
                findNavController().navigate(R.id.addressBookFragment)
            }else{
                Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener {
            //call viewmodel
            if(inputIsValid()) {
                viewModel.addContact(Contact(0, et_fname.text.toString(), et_lname.text.toString(), et_email.text.toString(), et_phone.text.toString(), et_address.text.toString()))
            }
        }
    }

    private fun inputIsValid() : Boolean{
        if(binding.etFname.text.toString().length < NAME_LENGTH){
            binding.etFname.error = getString(R.string.error_too_short)
            return false
        }
        if(binding.etLname.text.toString().length < NAME_LENGTH){
            binding.etLname.error = getString(R.string.error_too_short)
            return false
        }
        if(binding.etEmail.text.toString().length < EMAIL_LENGTH){
            binding.etEmail.error = getString(R.string.error_too_short)
            return false
        }
        if(binding.etPhone.text.toString().length < PHONE_LENGTH){
            binding.etPhone.error = getString(R.string.error_too_short)
            return false
        }
        if(binding.etAddress.text.toString().length < ADDRESS_LENGTH){
            binding.etAddress.error = getString(R.string.error_too_short)
            return false
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.add_address).isVisible = false
        menu.findItem(R.id.search_icon).isVisible = false
    }

    companion object {
        val NAME_LENGTH = 2
        val ADDRESS_LENGTH = 6
        val EMAIL_LENGTH = 6
        val PHONE_LENGTH = 11
    }

}