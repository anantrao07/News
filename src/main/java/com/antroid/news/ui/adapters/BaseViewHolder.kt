package com.antroid.news.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.antroid.news.model.DataItem

abstract class BaseViewHolder<T: DataItem>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(item: T)
    abstract fun recyclerView()
}