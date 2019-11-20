package com.example.flickr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr.network.FlickrApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val SPAN_COUNT = 3

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PhotoAdapter(this, getPhotoDimen())

        val recyclerView = findViewById<RecyclerView>(R.id.photosRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, SPAN_COUNT)
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val response = FlickrApi.RETROFIT_SERVICE.getPhotos()

            withContext(Dispatchers.Main) {
                Log.e("yooooo", "response: $response")
                adapter.addPhotos(response.responsePhotos.photos)
            }
        }
    }

    private fun getPhotoDimen(): Int {
        val displayMetrics = DisplayMetrics();
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        return screenWidth / SPAN_COUNT
    }
}
