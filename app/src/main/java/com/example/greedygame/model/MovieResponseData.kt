package com.example.greedygame.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponseData(@SerializedName("page") val  page:
                             String,@SerializedName("results") val  movieResponse: List<MovieResponse>) : Parcelable