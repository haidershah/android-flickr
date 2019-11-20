package com.example.flickr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickr.network.model.FlickrPhoto

class PhotoAdapter(private val context: Context) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val data = mutableListOf<FlickrPhoto>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoView = view as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        val url =
            "https://farm${item.farm}.staticflickr.com/${item.server}/${item.id}_${item.secret}.jpg"

        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .into(holder.photoView)
    }

    override fun getItemCount() = data.size

    fun addPhotos(photos: List<FlickrPhoto>) {
        data.addAll(photos)
        notifyDataSetChanged()
    }
}
