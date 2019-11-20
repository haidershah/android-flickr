package com.example.flickr.network.model

import com.google.gson.annotations.SerializedName

data class FlickrPhotos(

    @SerializedName("photo")
    val photos: List<FlickrPhoto>
)
