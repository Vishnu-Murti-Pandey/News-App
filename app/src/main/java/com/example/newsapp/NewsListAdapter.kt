package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemListBinding

class NewsListAdapter (private val listener: NewsItemClicked):
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    class NewsViewHolder (val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = NewsViewHolder(view)
        view.root.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder

    }

    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.title.text = currentItem.title
        holder.binding.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.binding.image)
    }

    override fun getItemCount(): Int {
        return items.size;
    }

}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}