package com.antroid.news.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antroid.news.model.DataItem

abstract class DataAdapter: RecyclerView.Adapter<BaseViewHolder<DataItem>>() {

    abstract fun provideViewHolder(parentView: ViewGroup): BaseViewHolder<DataItem>
    private var list: List<DataItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataItem> {
        return provideViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DataItem>, position: Int) {
        holder.bindView(list[position])
    }

    override fun onViewRecycled(holder: BaseViewHolder<DataItem>) {
        super.onViewRecycled(holder)
        holder.recyclerView()
    }

    fun updateData(newData: List<DataItem>){
        list = newData
        notifyDataSetChanged()
    }
}