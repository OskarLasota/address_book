package com.example.address_book.functionalities.addresslist

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.room.RxRoom
import com.example.address_book.R
import com.example.address_book.common.translateContactsToNames
import com.example.address_book.data.Contact
import com.example.address_book.databinding.FragmentAddressBookBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.TimeUnit


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
        setHasOptionsMenu(true)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.addresslistRecycler.setOnItemClickListener { _, _, position, _ ->
            findNavController().navigate(
                R.id.action_addressBookFragment_to_addressFragment,
                bundleOf(
                    "id" to addressList[position].id,
                    "name" to addressList[position].fname + " " + addressList[position].lname
                )
            )
        }
    }

    private fun setObservers() {
        viewModel.contacts.observe(viewLifecycleOwner, {
            it?.let { list ->
                addressList = list
                updateAdapter(list)
            }
        })
        viewModel.filtered.observe(viewLifecycleOwner, {
            updateAdapter(it)
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.search_icon)
        var searchView =
            searchItem.actionView as androidx.appcompat.widget.SearchView

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            searchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    //hide keyboard, not the best solution
                    val inputMethodManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    subscriber.onNext(newText!!)
                    if(newText!!.isEmpty()){
                        updateAdapter(addressList)
                    }
                    return true
                }
            })
        }).map { text -> text.toLowerCase().trim() }
            .debounce(250, TimeUnit.MILLISECONDS)
            .filter{ text ->
                text.isNotBlank()
            }
            .subscribe({
                viewModel.filterContacts(it)
            },{
                println(it.toString())
            })
    }

    private fun updateAdapter(list: List<Contact>){
        contactAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            list.translateContactsToNames()
        )
        binding.addresslistRecycler.apply {
            adapter = contactAdapter
        }
    }

}