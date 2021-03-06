package com.example.greedygame.model

import android.os.Parcelable
import com.example.greedygame.api.NetworkApi
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: Int,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genre_ids")
    val genres: Array<Int>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: Array<ProductionCompany>?,
    @SerializedName("production_countries")
    val production_countries: List<ProductuibCountry>?,
    @SerializedName("favoriteCount")
    val favoriteCount: Int?,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("revenue")
    val revenue: String?,
    @SerializedName("runtime")
    val runtime: List<SpokenLanguage>?,
    @SerializedName("spoken_languages")
    val spoken_languages: Int?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val vote_average: Double?,
    @SerializedName("vote_count")
    val vote_count: Int?) : Parcelable {
    fun getFullBackdropPath() =
        if (backdrop_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + backdrop_path

    fun getFullPosterPath() =
        if (poster_path.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + poster_path
    fun getFullProfilePath() =
        if (profileImageUrl.isNullOrBlank()) null else NetworkApi.SMALL_IMAGE + profileImageUrl


}