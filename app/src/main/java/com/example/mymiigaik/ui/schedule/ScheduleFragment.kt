package com.example.mymiigaik.ui.schedule

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentScgeduleBinding
import com.example.mymiigaik.ui.schedule.ItemsForRecyclerView.DayOfWeekItem
import com.example.mymiigaik.ui.schedule.ItemsForRecyclerView.LessonItem
import com.example.mymiigaik.ui.schedule.ItemsForRecyclerView.NoLessonsItem
import com.example.mymiigaik.ui.schedule.ItemsForRecyclerView.TeacherItem
import com.example.mymiigaik.ui.schedule.model.DayModel
import com.example.mymiigaik.ui.schedule.model.DaysOfWeekModel
import com.example.mymiigaik.ui.schedule.model.LessonModel
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

        viewModel.teachersList.observe(liveDataOwner) {
            val teacherItemList = it.map { teacherEntity ->
                TeacherItem(
                    teacherName = teacherEntity.name,
                    scheduleLinkOfTeacher = teacherEntity.scheduleLink
                ) {
                    viewModel.teacherIsPicked(teacherEntity.scheduleLink)
                    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                }
                //todo доразобраться с нажатием
            }
            recyclerViewAdapter.update(teacherItemList)
        }

        //selead pair model
        //у меня будет selead интерфейс, в котором будет хранится наследники  день, пара, пустота, нет занятий
        // получаю лист, потом просто все что там есть превращаю в model (лист сиоид интерйеса)
        // это все происзодит во viewModel
        // после чего на фрагменте обьекты этого силид ингтефейса преобразую в нужные item для recyclerView

        viewModel.scheduler.observe(liveDataOwner) {

            Log.d("Прием", "Сработал оббсерв ")
            val listOfScheduler: MutableList<RecyclerViewAdapter.Item> = mutableListOf()


            for (i in 0 until it.size){
                when (val item = it[i]) {
                    is DayModel -> {
                        if (i !=0){
                                if (it[i-1] is DayModel){
                                    listOfScheduler.add(NoLessonsItem())
                            }
                        }
                        listOfScheduler.add(DayOfWeekItem(nameOfTheDayOfTheWeek = item.day)) //todo - когда нибудь переделать, но пока можно оставить
                        if (i == it.size-1){
                            listOfScheduler.add(NoLessonsItem())
                        }
                    }
                    is LessonModel -> {
                        listOfScheduler.add(
                            LessonItem(
                                additionalInfo = item.additionalInfo,
                                classroom = item.classroom,
                                day = item.day,
                                group = item.group,
                                lesson = item.lesson,
                                lessonType = item.lessonType,
                                subject = item.subject,
                                weekType = item.weekType
                            )
                        )

                            //todo Надо переделать cardView, чтобы они нормально взаимодействовали
                            //todo сделать, так чтобы когда переключаешься на другую вкладку (новости или профиль) у меня полностью сбрасывался RV
                    }
                }
            }
            recyclerViewAdapter.update(listOfScheduler)
        }


        viewModel.whatButtonIsPicked.observe(liveDataOwner){
            binding.editText.setText("")
            when(it){

                TypeOfButtons.Groups -> {
                    binding.editText.setHint("Введите группу")
                    binding.nameOfSearchTextView.setText(R.string.name_of_search_group)
                    binding.searchGroupButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_text_button_search_miigaik))
                    binding.searchTeacherButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchAuditoriumButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchExamsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

                    binding.searchGroupButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_blue_button_search_miigaik
                        )
                    )
                    binding.searchTeacherButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchAuditoriumButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchExamsButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                }

                TypeOfButtons.Teachers -> {
                    binding.editText.setHint("Введите преподавателя")
                    binding.nameOfSearchTextView.setText(R.string.name_of_search_teacher)
                    binding.nameOfSearchTextView.setText(R.string.name_of_search_group)
                    binding.searchGroupButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchTeacherButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_text_button_search_miigaik))
                    binding.searchAuditoriumButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchExamsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchGroupButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchTeacherButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_blue_button_search_miigaik
                        )
                    )
                    binding.searchAuditoriumButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchExamsButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                }

                TypeOfButtons.Classroom -> {
                    binding.editText.setHint("Введите номер аудитории")
                    binding.nameOfSearchTextView.setText(R.string.name_of_search_auditorium)
                    binding.searchGroupButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchTeacherButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchAuditoriumButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_text_button_search_miigaik))
                    binding.searchExamsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

                    binding.searchGroupButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchTeacherButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchAuditoriumButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_blue_button_search_miigaik
                        )
                    )
                    binding.searchExamsButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                }

                TypeOfButtons.Exams -> {
                    binding.editText.setHint("Введите группу")
                    binding.nameOfSearchTextView.setText(R.string.name_of_search_exams)
                    binding.searchGroupButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchTeacherButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchAuditoriumButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    binding.searchExamsButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white_text_button_search_miigaik))
                    binding.searchGroupButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchTeacherButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchAuditoriumButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grey_button_search_miigaik
                        )
                    )
                    binding.searchExamsButton.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.dark_blue_button_search_miigaik
                        )
                    )
                }
            }

        }



        binding.searchGroupButton.setOnClickListener {
             //todo - нажатие на каждую из трех кнопок говорит livedata  - что пора удалить текст и покрасить кнопку в нужные цвета, ставится hint
            //одна в лайвдата с 4 енамами, и в зависимости от этого enuma ставится все
            //todo - editext
            //todo - отдельную лайдату для введего текста - чтобы она сеталась в edittext - когда новое значнеие
            viewModel.installTypeOfSearch(TypeOfButtons.Groups)
        }

        binding.searchTeacherButton.setOnClickListener {
            viewModel.installTypeOfSearch(TypeOfButtons.Teachers)
        }

        binding.searchAuditoriumButton.setOnClickListener {
            viewModel.installTypeOfSearch(TypeOfButtons.Classroom)
        }

        binding.searchExamsButton.setOnClickListener {
            viewModel.installTypeOfSearch(TypeOfButtons.Exams)
        }

        viewModel.errorEmptyList.observe(liveDataOwner) {
            val toast = Toast.makeText(requireContext(), "Ничего не найдено", Toast.LENGTH_SHORT)
            toast.show()
        }

        binding.showOrHideMenuButtonTextView.setOnClickListener {
            when (binding.advancedSearchSelection.visibility) {
                View.GONE -> binding.advancedSearchSelection.visibility = View.VISIBLE
                View.VISIBLE -> binding.advancedSearchSelection.visibility = View.GONE
            }
        }

        //todo() - сделать, что при скрытие меню убирался и обнулялся RecyclerView
        //todo() - доделать, чтобы при нажатии на другой вариант поиска цвета сбрасывались, а выбранное значение сохранялось до преркащения работы приложения


    }

}