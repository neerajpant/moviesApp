package com.example.greedygame.model

import android.os.Parcelable
import com.example.greedygame.api.NetworkApi
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorDetails(@SerializedName("name")
                          val name: String,
                          @SerializedName("username")
                          val username: String,
                          @SerializedName("avatar_path")
                          val avatar_path: String,
                         @SerializedName("rating")
                         val rating: String


): Parcelable {
    fun getFullBackdropPath() =
        if (avatar_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + avatar_path

    fun getFullPosterPath() =
        if (avatar_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + avatar_path
}