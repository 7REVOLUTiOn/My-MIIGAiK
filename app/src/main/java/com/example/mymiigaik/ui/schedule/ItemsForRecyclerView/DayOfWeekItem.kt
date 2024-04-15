package com.example.mymiigaik.ui.schedule.ItemsForRecyclerView

import com.example.myapplication.R
import com.example.myapplication.databinding.DayOfWeekItemBinding
import com.example.mymiigaik.ui.schedule.RecyclerViewAdapter

class DayOfWeekItem(
    private val nameOfTheDayOfTheWeek: String
) : RecyclerViewAdapter.Item {


    override fun getItemViewType(): Int = R.layout.day_of_week_item

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = DayOfWeekItemBinding.bind(this)
            binding.nameOfTheDayOfWeek.text = nameOfTheDayOfTheWeek
        }

    }

}