package com.cricketclash.dafaextra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cricketclash.dafaextra.model.Article
import com.squareup.picasso.Picasso

class RVAdapter (val context: Context, val articles: List<Article>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        var cardImage = itemView.findViewById<ImageView>(R.id.cardImage)
        var cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
        var cardAuthor: TextView = itemView.findViewById(R.id.cardAuthor)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.raw_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article: Article = articles[position]

        holder.cardTitle.text = article.title

        if (article.author.toString() == "null") {
            holder.cardAuthor.text = ""
        } else {
            holder.cardAuthor.text = article.author.toString()
        }

        Picasso
            .get()
            .load(article.urlToImage.toString())
            .fit()
            .centerCrop()
            .error(R.drawable.dafaplaceholder)
            .into(holder.cardImage)

    }

    override fun getItemCount(): Int {
        return articles.size
    }
}