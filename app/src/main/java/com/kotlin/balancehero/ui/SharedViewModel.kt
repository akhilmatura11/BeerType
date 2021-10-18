package com.kotlin.balancehero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.data.DataRepository
import com.kotlin.balancehero.ui.adapter.Tab1Adapter
import com.kotlin.balancehero.ui.adapter.Tab2Adapter

class SharedViewModel : ViewModel() {
    private val mTab1Adapter: Tab1Adapter = Tab1Adapter(this)
    private val mTab2Adapter: Tab2Adapter = Tab2Adapter(this)
    private val beersList: LiveData<List<Beers>>
    private val mSelectedItem = MutableLiveData<Beers>()

    companion object {
        private var pageCounter = 1
    }

    init {
        val dataRepository = DataRepository.getInstance()
        dataRepository.fetchData(1)
        beersList = dataRepository.getBeersList()
    }

    fun getBeers(): LiveData<List<Beers>> = beersList

    fun getSelectedItem(): LiveData<Beers> = mSelectedItem

    fun getTab1Adapter(): Tab1Adapter = mTab1Adapter

    fun getTab2Adapter(): Tab2Adapter = mTab2Adapter

    fun updateTab1Adapter(list: List<Beers>) {
        mTab1Adapter.updateList(list)
    }

    fun updateTab2Adapter(list: List<Beers>) {
        mTab2Adapter.updateList(list)
    }

    fun onItemClick(beer: Beers) {
        mSelectedItem.value = beer
    }

    fun onCheckboxClicked(checked: Boolean, beer: Beers) {
        mTab1Adapter.updateItem(beer, checked)
        mTab2Adapter.updateItem(beer, checked)
    }

    fun getNextPage() {
        pageCounter++
        DataRepository.getInstance().fetchData(pageCounter)
    }
}