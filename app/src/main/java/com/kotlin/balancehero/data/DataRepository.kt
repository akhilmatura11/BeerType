package com.kotlin.balancehero.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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

//    fun fetchData(pageNumber: Int) {
//        val call = ApiClient.getClient().create(ApiInterface::class.java)
//            .GetList(pageNumber.toString())
//        call.enqueue(object : Callback<List<Beers>> {
//            override fun onResponse(call: Call<List<Beers>>, response: Response<List<Beers>>) {
//                if (response.isSuccessful && response.body() != null) {
//                    beersList.value = response.body()!!
//                }
//            }
//
//            override fun onFailure(call: Call<List<Beers>>, t: Throwable) {
//                Log.e("Data Repository", t.toString())
//            }
//        })
//    }
}
