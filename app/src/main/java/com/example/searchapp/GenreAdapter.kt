package com.example.searchapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchapp.databinding.RecyclerviewItemBinding


class GenreAdapter(
    private val genres: ArrayList<String>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {


    inner class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RecyclerviewItemBinding.bind(itemView)
        fun bind(genre: String) {
            binding.textViewRecycleItem.text = genre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        return GenreViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val data = genres[position]
        holder.bind(data)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClickListener(data)
        }
    }

    override fun getItemCount(): Int = genres.size

}