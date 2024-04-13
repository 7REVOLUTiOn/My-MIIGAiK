package com.example.mymiigaik.ui.schedule

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentScgeduleBinding
import com.example.mymiigaik.ui.schedule.ItemsForRecyclerView.TeacherItem
import com.example.mymiigaik.utils.LiveDataUtils.liveDataOwner
import org.koin.androidx.viewmodel.ext.android.viewModel


class ScheduleFragment : Fragment(R.layout.fragment_scgedule) {



    private val binding by viewBinding(FragmentScgeduleBinding::bind)
    private val viewModel by viewModel<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter

        binding.editText.doAfterTextChanged {
            viewModel.setSearchText(it.toString())
        }

        viewModel.teachersList.observe(liveDataOwner){
            val teacherItemList = it.map { teacherEntity ->
                TeacherItem(teacherName = teacherEntity.name, scheduleLinkOfTeacher = teacherEntity.scheduleLink){
                    viewModel.teacherIsPicked(teacherEntity.scheduleLink)
                }
                //todo доразобраться с нажатием
            }
            recyclerViewAdapter.update(teacherItemList)
        }

        viewModel.scheduler.observe(liveDataOwner){

        }



       /* viewModel.whatButtonIsPicked.observe(liveDataOwner){
           when (viewModel.whatButtonIsPicked){
               TypeOfButtons.Groups -> {

               }

               TypeOfButtons.Teachers -> {

               }

               TypeOfButtons.Classroom -> {

               }

               TypeOfButtons.Exams -> {

               }

           }


        }*/


        binding.searchGroupButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_group)
            binding.editText.setText("") //todo - нажатие на каждую из трех кнопок говорит livedata  - что пора удалить текст и покрасить кнопку в нужные цвета, ставится hint
            //одна в лайвдата с 4 енамами, и в зависимости от этого enuma ставится все
            //todo - editext
            //todo - отдельную лайдату для введего текста - чтобы она сеталась в edittext - когда новое значнеие
            binding.editText.setHint("Введите группу")
            viewModel.installTypeOfSearch(TypeOfButtons.Groups)


        }

        binding.searchTeacherButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_grey_text_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.editText.setText("")
            binding.nameOfSearchTextView.setText(R.string.name_of_search_teacher)
            binding.editText.setHint("Введите преподавателя")
            viewModel.installTypeOfSearch(TypeOfButtons.Teachers)
        }

        binding.searchAuditoriumButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_auditorium)
            binding.editText.setHint("Введите номер аудитории")
            viewModel.installTypeOfSearch(TypeOfButtons.Classroom)
            binding.editText.setText("")
        }

        binding.searchExamsButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_exams)
            binding.editText.setHint("Введите группу")
            viewModel.installTypeOfSearch(TypeOfButtons.Exams)
            binding.editText.setText("")
        }

        viewModel.errorEmptyList.observe(liveDataOwner){
            val toast = Toast.makeText(requireContext(),"Ничего не найдено", Toast.LENGTH_SHORT,)
            toast.show()
        }

        binding.showOrHideMenuButtonTextView.setOnClickListener {
            /*when (binding.showOrHideMenuButtonTextView.text) {
                getString(R.string.show_the_menu_schedule) ->  binding.showOrHideMenuButtonTextView.setText(R.string.hide_the_menu_schedule)
                getString(R.string.hide_the_menu_schedule) ->  binding.showOrHideMenuButtonTextView.setText(R.string.show_the_menu_schedule)
            } //todo - доделать чтобы показывалось (скрыть меню/показать меню)*/

            //if (binding.showOrHideMenuButtonTextView.text == getString(R.string.show_the_menu_schedule))
            //binding.showOrHideMenuButtonTextView.setText(R.string.hide_the_menu_schedule)
            when (binding.advancedSearchSelection.visibility){
                View.GONE -> binding.advancedSearchSelection.visibility = View.VISIBLE
                View.VISIBLE -> binding.advancedSearchSelection.visibility = View.GONE
                //todo() - можно посмотреть что будет если использовать isVisible и обязательно найти отличия
            }
        }

        //todo() - сделать, что при скрытие меню убирался и обнулялся RecyclerView
        //todo() - доделать, чтобы при нажатии на другой вариант поиска цвета сбрасывались, а выбранное значение сохранялось до преркащения работы приложения


    }

}