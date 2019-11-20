package com.example.flickr.network

import com.example.flickr.network.model.FlickrResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.flickr.com")
    .build()

interface FlickrApiService {

    @GET("services/rest/?method=flickr.photos.getRecent&api_key=6e065e8f98dce8f7550425b88416d3eb&page=1&format=json&nojsoncallback=1")
    suspend fun getPhotos(): FlickrResponse
}

object FlickrApi {
    val RETROFIT_SERVICE: FlickrApiService = retrofit.create(
        FlickrApiService::class.java)
}
