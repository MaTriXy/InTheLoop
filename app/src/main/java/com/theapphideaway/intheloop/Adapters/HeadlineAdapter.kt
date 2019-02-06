package com.theapphideaway.intheloop.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.theapphideaway.intheloop.ArticleActivity
import com.theapphideaway.intheloop.Models.Article
import com.theapphideaway.intheloop.Models.HeadlineResponse
import com.theapphideaway.intheloop.R

class HeadlineAdapter(private val headlineResponse: HeadlineResponse, private val context: Context):
    RecyclerView.Adapter<HeadlineAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {

        var noteCard = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(noteCard)
    }

    override fun getItemCount(): Int {
        return headlineResponse.articles.count()
    }

    fun goToArticle(url: String){
        var intent = Intent(context, ArticleActivity::class.java)
        intent.putExtra("Url", url)
        ContextCompat.startActivity(context, intent, null)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNote(headlineResponse.articles[position])

        holder.itemView.setOnClickListener { goToArticle(headlineResponse.articles[position].url) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNote(article: Article) {
            var titleText: TextView = itemView.findViewById(R.id.title) as TextView
            var newsImage = itemView.findViewById(R.id.news_image) as ImageView

            titleText.text = article.title

            Picasso.get().load(article.urlToImage).into(newsImage)
        }

    }
}