package com.example.mymiigaik.ui.schedule

import android.os.Bundle
import android.view.View
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

            viewModel.getAllTeachersByName(it.toString())
        }


        viewModel.teachersList.observe(liveDataOwner){
            val teacherItemList = it.map { teacherEntity ->
                TeacherItem(teacherName = teacherEntity.name, scheduleLinkOfTeacher = teacherEntity.scheduleLink, isPicked = viewModel.teacherIsPicked(scheduleLink = teacherEntity.scheduleLink))
                //todo доразобраться с нажатием
            }
            recyclerViewAdapter.update(teacherItemList)
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