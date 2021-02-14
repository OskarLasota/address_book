package com.example.address_book.functionalities.newaddress

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.address_book.R
import com.example.address_book.data.Contact
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_address.*

@AndroidEntryPoint
class NewAddressFragment : Fragment(R.layout.fragment_new_address) {

    private val viewModel by viewModels<NewAddressViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setListeners()
        viewModel.getContacts()
    }

    private fun setListeners(){
        btn_save.setOnClickListener {
            //call viewmodel
            if(inputIsValid()) {
                viewModel.addContact(Contact(0, et_fname.text.toString(), et_lname.text.toString(), et_email.text.toString(), et_phone.text.toString(), et_address.text.toString()))
            }
        }
    }

    private fun inputIsValid() : Boolean{
        if(et_fname.text.toString().length < NAME_LENGTH){
            et_fname.error = getString(R.string.error_too_short)
            return false
        }
        if(et_lname.text.toString().length < NAME_LENGTH){
            et_lname.error = getString(R.string.error_too_short)
            return false
        }
        if(et_email.text.toString().length < EMAIL_LENGTH){
            et_address.error = getString(R.string.error_too_short)
            return false
        }
        if(et_phone.text.toString().length < PHONE_LENGTH){
            et_phone.error = getString(R.string.error_too_short)
            return false
        }
        if(et_address.text.toString().length < ADDRESS_LENGTH){
            et_address.error = getString(R.string.error_too_short)
            return false
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.add_address).isVisible = false
    }

    companion object {
        val NAME_LENGTH = 2
        val ADDRESS_LENGTH = 6
        val EMAIL_LENGTH = 6
        val PHONE_LENGTH = 11
    }

}