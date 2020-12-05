package com.antroid.news.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.antroid.news.R
import com.antroid.news.database.NewsDataTable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class NewsViewHolder(itemView: View): BaseViewHolder<NewsDataTable>(itemView) {

    private val newsHeadlineTv = itemView.findViewById<TextView>(R.id.headline_tv)
    private val newsImagesIv = itemView.findViewById<ImageView>(R.id.news_iv)
    private val newsPublishedAtTv = itemView.findViewById<TextView>(R.id.date_tv)

    override fun recyclerView() {
    }

    override fun bindView(item: NewsDataTable) {
        newsHeadlineTv.text = item.title
        newsPublishedAtTv.text = item.readablePublishedAt
        Glide.with(itemView.context)
            .load(item.typeAttributes.imageLarge)
            .placeholder(R.drawable.news_image_placeholder)
            .error(R.drawable.news_image_placeholder)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(newsImagesIv)
    }

    companion object{
        fun newInstance(parentView: ViewGroup): BaseViewHolder<NewsDataTable>{
            return NewsViewHolder(
                LayoutInflater.from(parentView.context).inflate(
                    R.layout.news_item,
                    parentView,
                    false
                )
            )
        }
    }
}