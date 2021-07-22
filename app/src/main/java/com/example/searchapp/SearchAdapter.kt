package com.example.searchapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchapp.model.PhotosResponse.Source

class SearchAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var sourceList: List<Source> = emptyList()

    fun setImageList(data: List<Source>) {
        sourceList = data
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val author: TextView = itemView.findViewById(R.id.textViewAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val sourceListItem = sourceList[position]
        
        Glide.with(context).load(sourceListItem.src?.tiny).into(holder.image)

        holder.author.text = sourceListItem.photographer
    }

    override fun getItemCount() = sourceList.size
}