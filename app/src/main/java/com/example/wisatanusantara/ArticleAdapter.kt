package com.example.wisatanusantara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ArticleAdapter(private val listArticle: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {


    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listArticle[position]
        holder.title.text = article.title


        val combinedOverview = "Lokasi: ${article.location}\n\n${article.overview}"; holder.overview.text = combinedOverview // *INI YANG BENAR*



        holder.photo.setImageResource(article.imageResId)

        holder.itemView.setOnClickListener {

            if (::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClicked(article)
            }
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo: ImageView = itemView.findViewById(R.id.img_item_photo)
        var title: TextView = itemView.findViewById(R.id.tv_item_title)
        var overview: TextView = itemView.findViewById(R.id.tv_item_overview)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}