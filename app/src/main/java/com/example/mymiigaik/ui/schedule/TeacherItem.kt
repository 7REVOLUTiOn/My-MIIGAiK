package com.example.mymiigaik.ui.schedule

import com.example.myapplication.R
import com.example.myapplication.databinding.TeacherItemBinding

class TeacherItem(
    private val teacherName:String,
    private val scheduleLinkOfTeacher: String,
    private val isPicked: (scheduleLink:String) -> Unit
):RecyclerViewAdapter.Item {


    override fun getItemViewType(): Int = R.layout.teacher_item

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = TeacherItemBinding.bind(this)
            binding.teacher.text = teacherName
        }
        holder.itemView.setOnClickListener {
            isPicked(scheduleLinkOfTeacher)
        }
    }

}