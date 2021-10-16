package com.kotlin.balancehero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.data.BeersPagingSource
import com.kotlin.balancehero.http.ApiClient
import com.kotlin.balancehero.http.ApiInterface
import com.kotlin.balancehero.ui.adapter.Tab1Adapter
import com.kotlin.balancehero.ui.adapter.Tab2Adapter
import kotlinx.coroutines.flow.Flow

class SharedViewModel : ViewModel() {
    private val mTab1Adapter: Tab1Adapter = Tab1Adapter(this)
    private val mTab2Adapter: Tab2Adapter = Tab2Adapter(this)

    private val mSelectedItem = MutableLiveData<Beers>()
    private var beers: Flow<PagingData<Beers>>

    init {
        beers = Pager(PagingConfig(pageSize = 20)) {
            BeersPagingSource(ApiClient.getClient().create(ApiInterface::class.java))
        }.flow.cachedIn(viewModelScope)
    }

    fun getBeers(): Flow<PagingData<Beers>> = beers

    fun getSelectedItem(): LiveData<Beers> = mSelectedItem

    fun getTab1Adapter(): Tab1Adapter = mTab1Adapter

    fun getTab2Adapter(): Tab2Adapter = mTab2Adapter

    fun onItemClick(beer: Beers) {
        mSelectedItem.value = beer
    }

    fun onCheckboxClicked(checked: Boolean, beer: Beers, fromLayout: Boolean) {
        mTab2Adapter.updateItem(beer, checked, fromLayout)
        mTab1Adapter.updateItem(beer, checked, fromLayout)
    }
}