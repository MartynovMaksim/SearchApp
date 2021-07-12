package com.example.searchapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchapp.model.HitsResponse.Image

class SearchAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var imageList: List<Image> = emptyList()

    fun setImageList(data: List<Image>) {
        imageList = data
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val likes: TextView = itemView.findViewById(R.id.textViewLikes)
        val tags: TextView = itemView.findViewById(R.id.textViewTags)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val listItem = imageList[position]

        Glide.with(context).load(listItem.previewURL).into(holder.image)

        holder.likes.text = listItem.likes
        holder.tags.text = listItem.tags
    }

    override fun getItemCount() = imageList.size
}