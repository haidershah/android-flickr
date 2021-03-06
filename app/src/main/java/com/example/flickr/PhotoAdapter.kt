package com.example.flickr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.flickr.network.model.FlickrPhoto

class PhotoAdapter(
    private val context: Context,
    private val photoDimen: Int
) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

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

        // resize width, but keep original height, does not keep aspect ratio
        Glide.with(context)
            .load(url)
            .override(photoDimen, Target.SIZE_ORIGINAL) // original height
            .into(holder.photoView)

        // square image
//        Glide.with(context)
//            .load(url)
//            .override(photoDimen)
//            .centerCrop()
//            .into(holder.photoView)
    }

    override fun getItemCount() = data.size

    fun addPhotos(photos: List<FlickrPhoto>) {
        data.addAll(photos)
        notifyDataSetChanged()
    }
}
