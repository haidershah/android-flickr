package com.example.flickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.network.FlickrApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PhotoAdapter(this)

        val recyclerView = findViewById<RecyclerView>(R.id.photosRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val response = FlickrApi.RETROFIT_SERVICE.getPhotos()

            withContext(Dispatchers.Main) {
                Log.e("yooooo", "response: $response")
                adapter.addPhotos(response.responsePhotos.photos)
            }
        }
    }
}
