package com.example.mymiigaik.ui.schedule.ItemsForRecyclerView

import com.example.myapplication.R
import com.example.myapplication.databinding.NoLessonsItemBinding
import com.example.mymiigaik.ui.schedule.RecyclerViewAdapter

class NoLessonsItem() : RecyclerViewAdapter.Item {


    override fun getItemViewType(): Int = R.layout.no_lessons_item

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = NoLessonsItemBinding.bind(this)
        }
    }

}