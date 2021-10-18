package com.kotlin.balancehero.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlin.balancehero.http.ApiClient
import com.kotlin.balancehero.http.ApiInterface
import com.kotlin.balancehero.http.HttpConstant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository {
    private val beersList = MutableLiveData<List<Beers>>()

    companion object {
        private var INSTANCE: DataRepository? = null
        fun getInstance() = INSTANCE
            ?: DataRepository().also {
                INSTANCE = it
            }
    }

    fun getBeersList(): LiveData<List<Beers>> = beersList

    fun fetchData(pageNumber: Int) {
        val call = ApiClient.getClient(HttpConstant.BASE_URL).create(ApiInterface::class.java)
            .getList(pageNumber)
        call.enqueue(object : Callback<List<Beers>> {
            override fun onResponse(call: Call<List<Beers>>, response: Response<List<Beers>>) {
                if (response.isSuccessful && response.body() != null) {
                    beersList.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<List<Beers>>, t: Throwable) {
                Log.e("Data Repository", t.toString())
            }
        })
    }

    fun update(beer: Beers, checked: Boolean) {
        val index: Int = beer.id - 1
        beersList.value?.get(index)?.checkbox = checked
    }
}
