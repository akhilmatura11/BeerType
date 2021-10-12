package com.kotlin.balancehero.http

import com.kotlin.balancehero.data.Beers
import com.kotlin.balancehero.data.BeersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("beers")
    suspend fun getList(@Query("page") page: Int): BeersResponse<Beers>

}