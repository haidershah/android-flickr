package com.example.flickr.network.model

import com.google.gson.annotations.SerializedName

data class FlickrPhoto(

    @SerializedName("id")
    val id: String,

    @SerializedName("farm")
    val farm: Int,

    @SerializedName("server")
    val server: String,

    @SerializedName("secret")
    val secret: String
)
//"https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"