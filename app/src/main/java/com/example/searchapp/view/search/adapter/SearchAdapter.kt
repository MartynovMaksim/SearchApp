package com.example.searchapp.view.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchapp.R
import com.example.searchapp.model.search.data.PhotosResponse.Source
import com.example.searchapp.view.search.ItemClickListener

class SearchAdapter(private val context: Context,
                    private val itemClickListener: ItemClickListener) :
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

        holder.itemView.setOnClickListener {
            sourceListItem.src?.original?.let { itemClickListener.onItemClickListener(it) }
        }
    }

    override fun getItemCount() = sourceList.size
}