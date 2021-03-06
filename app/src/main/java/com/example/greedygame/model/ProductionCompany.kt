package com.example.greedygame.model

import android.os.Parcelable
import com.example.greedygame.api.NetworkApi
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompany(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logo_path: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val origin_country: String
) : Parcelable {
    fun getFullBackdropPath() =
        if (logo_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + logo_path

    fun getFullPosterPath() =
        if (logo_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + logo_path
}
