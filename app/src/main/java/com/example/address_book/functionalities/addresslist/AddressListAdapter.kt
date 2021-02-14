package com.example.address_book.functionalities.addresslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.address_book.R
import com.example.address_book.data.Contact
import com.example.address_book.databinding.CardContactBinding

class AddressListAdapter(private val data : List<Contact>) : RecyclerView.Adapter<AddressListAdapter.ViewHolder>() {

    private lateinit var binding : CardContactBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater : LayoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.card_contact, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }



    class ViewHolder(private val binding : CardContactBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(contact : Contact){
            binding.contact = contact
        }

    }




}