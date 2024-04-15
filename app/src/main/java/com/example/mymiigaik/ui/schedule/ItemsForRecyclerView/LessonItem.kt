package com.example.mymiigaik.ui.schedule.ItemsForRecyclerView

import com.example.myapplication.R
import com.example.myapplication.databinding.LessonItemBinding
import com.example.myapplication.databinding.TeacherItemBinding
import com.example.mymiigaik.ui.schedule.RecyclerViewAdapter

class LessonItem(
    private val additionalInfo: String?,
    private val classroom: String?,
    private val day: String?,
    private val group: String?,
    private val lesson: String?,
    private val lessonType: String?,
    private val subject: String?,
    private val weekType: String?
) : RecyclerViewAdapter.Item {


    override fun getItemViewType(): Int = R.layout.lesson_item

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {
        holder.itemView.apply {
            val binding = LessonItemBinding.bind(this)
            binding.nameOfLesson.text = subject
            binding.typeOfLesson.text = lessonType
            binding.classroomName.text = classroom
            binding.nameOfGroup.text = group
            binding.LessonNumber.text = lesson
        }

    }
}
