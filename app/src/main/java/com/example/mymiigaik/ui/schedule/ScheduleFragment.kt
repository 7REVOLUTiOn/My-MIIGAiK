package com.example.mymiigaik.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentScgeduleBinding
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
                TeacherItem(teacherName = teacherEntity.name, scheduleLinkOfTeacher = teacherEntity.scheduleLink, )
                //todo доразобраться с нажатием
            }
            recyclerViewAdapter.update(teacherItemList)
        }



        viewModel.whatButtonIsPicked.observe(liveDataOwner){
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
        }


        binding.searchGroupButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_group)
            binding.editText.setText("")
            viewModel.installTypeOfSearch(TypeOfButtons.Groups)
        }

        binding.searchTeacherButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_teacher)
            binding.editText.setText("")
            viewModel.installTypeOfSearch(TypeOfButtons.Teachers)
        }

        binding.searchAuditoriumButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_auditorium)
            binding.editText.setText("")
            viewModel.installTypeOfSearch(TypeOfButtons.Classroom)
        }

        binding.searchExamsButton.setOnClickListener {
            binding.searchGroupButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))
            binding.searchTeacherButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchAuditoriumButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grey_button_search_miigaik))
            binding.searchExamsButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue_button_search_miigaik))

            binding.nameOfSearchTextView.setText(R.string.name_of_search_exams)
            binding.editText.setText("")
            viewModel.installTypeOfSearch(TypeOfButtons.Exams)
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