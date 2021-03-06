package com.example.greedygame.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewResponse(
    @SerializedName("author")
    val author: String?,
    @SerializedName("author_details")
    val author_details: AuthorDetails?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("created_at")
    val created_at: String)
     : Parcelable {


}