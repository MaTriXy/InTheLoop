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
import java.text.SimpleDateFormat

class SportsAdapter (private val headlineResponse: HeadlineResponse, private val context: Context):
    RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {

        var noteCard = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false)

        return ViewHolder(noteCard)
    }

    override fun getItemCount(): Int {
        return headlineResponse.articles.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindNote(headlineResponse.articles[position])

        holder.itemView.setOnClickListener { goToArticle(headlineResponse.articles[position].url) }
    }

    fun goToArticle(url: String){
        var intent = Intent(context, ArticleActivity::class.java)
        intent.putExtra("Url", url)
        ContextCompat.startActivity(context, intent, null)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindNote(article: Article) {
            var titleText: TextView = itemView.findViewById(R.id.title) as TextView
            var sourceText: TextView = itemView.findViewById(R.id.source_text_view) as TextView
            var dateText: TextView = itemView.findViewById(R.id.date_text_view) as TextView
            var newsImage = itemView.findViewById(R.id.news_image) as ImageView

            titleText.text = article.title
            sourceText.text = article.source.name

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = dateFormat.parse(article.publishedAt)//You will get date object relative to server/client timezone wherever it is parsed
            val formatter = SimpleDateFormat("DD, MMM")
            //If you need time just put specific format for time like 'HH:mm:ss'
            val dateStr = formatter.format(date)

            dateText.text = dateStr

            Picasso.get().load(article.urlToImage).into(newsImage)
        }

    }
}