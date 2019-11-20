package com.example.flickr.network.model

import com.google.gson.annotations.SerializedName

data class FlickrResponse(

    @SerializedName("photos")
    val responsePhotos: FlickrPhotos
)
