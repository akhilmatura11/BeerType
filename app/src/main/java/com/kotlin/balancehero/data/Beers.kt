package com.kotlin.balancehero.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class BeersResponse<T>(
    @Json(name = "") val result: List<T> = listOf()
)

@Parcelize
data class Beers(
    val id: Int,
    val name: String,
    val image_url: String,
    var checkbox: Boolean
) : Parcelable {
    constructor(beer: Beers, checked: Boolean) :
            this(beer.id, beer.name, beer.image_url, checked)
}
