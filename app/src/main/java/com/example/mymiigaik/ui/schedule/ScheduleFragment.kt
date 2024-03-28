package com.example.mymiigaik.ui.schedule

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentScgeduleBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scgedule, container, false)
    }

    private val binding by viewBinding(FragmentScgeduleBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showOrHideMenuButtonTextView.setOnClickListener {
            when (binding.showOrHideMenuButtonTextView.text) {
                getString(R.string.show_the_menu_schedule) ->  binding.showOrHideMenuButtonTextView.setText(R.string.hide_the_menu_schedule)
                getString(R.string.hide_the_menu_schedule) ->  binding.showOrHideMenuButtonTextView.setText(R.string.show_the_menu_schedule)
            } //todo - доделать чтобы показывалось (скрыть меню/показать меню)

            if (binding.showOrHideMenuButtonTextView.text == getString(R.string.show_the_menu_schedule))
            //binding.showOrHideMenuButtonTextView.setText(R.string.hide_the_menu_schedule)
            when (binding.advancedSearchSelection.visibility){
                View.GONE -> binding.advancedSearchSelection.visibility = View.VISIBLE
                View.VISIBLE -> binding.advancedSearchSelection.visibility = View.GONE
                //todo() - можно посмотреть что будет если использовать isVisible и обязательно найти отличия
            }
        }

        binding.searchGroupButton.setOnClickListener {
            binding.searchGroupButton.setTextColor(R.color.white_text_button_search_miigaik)
            binding.searchExamsButton.setBackgroundColor(R.color.dark_blue_button_search_miigaik)
            binding.searchTeacherButton.setTextColor(R.color.grey_button_search_miigaik)
            binding.searchTeacherButton.setBackgroundColor(R.color.dark_grey_text_button_search_miigaik)
            binding.searchAuditoriumButton.setTextColor(R.color.grey_button_search_miigaik)
            binding.searchAuditoriumButton.setBackgroundColor(R.color.dark_grey_text_button_search_miigaik)
            binding.searchExamsButton.setTextColor(R.color.grey_button_search_miigaik)
            binding.searchExamsButton.setBackgroundColor(R.color.dark_grey_text_button_search_miigaik)
        }
        //todo() - доделать, чтобы при нажатии на другой вариант поиска цвета сбрасывались, а выбранное значение сохранялось до преркащения работы приложения


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScgeduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}