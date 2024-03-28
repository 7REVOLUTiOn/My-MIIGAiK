package com.example.mymiigaik.ui.schedule

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    val dataList = mutableListOf<Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(newList: List<Item>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(
            viewType,
            parent,
            false
        )
        val holder = Holder(itemView)
        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        dataList[position].onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].getItemViewType()
    }

    interface Item {
        fun getItemViewType(): Int
        fun onBindViewHolder(holder: Holder, position: Int)
    }


}