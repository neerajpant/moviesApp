package com.example.greedygame.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewsData(@SerializedName("results") val result:List<ReviewResponse>?): Parcelable {


}