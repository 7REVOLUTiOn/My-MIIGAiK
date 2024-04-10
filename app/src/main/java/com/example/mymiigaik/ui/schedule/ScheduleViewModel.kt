package com.example.mymiigaik.ui.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.utils.LiveDataUtils.asLiveData
import com.example.mymiigaik.utils.LiveDataUtils.mValue
import com.example.mymiigaik.utils.SearchException
import com.example.mymiigaik.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel(
    private val getAllTeachersByUserInputUseCase: suspend (name: String) -> TRezult<List<TeacherSearchEntity>> // Запрос на получение листа имен преподавателей
) : ViewModel() {


    private val _teachersList = MutableLiveData<List<TeacherSearchEntity>>()
    val teachersList = _teachersList.asLiveData()

    private val _errorEmptyList = MutableLiveData<SearchException>()
    val errorEmptyList = _errorEmptyList.asLiveData()

    private val dataTeacherList = mutableListOf<TeacherSearchEntity>()


    private val _whatButtonIsPicked = MutableLiveData<TypeOfButtons>()
    val whatButtonIsPicked = _whatButtonIsPicked.asLiveData()

    private var searchJob: Job? = null


    fun setSearchText(searchText: String) {
        if (searchText.isEmpty()) {
            searchJob?.cancel()
            _teachersList.mValue = dataTeacherList
        } else {
            startTheSearch(searchText)
        }
    }

    fun startTheSearch(inputOfUser: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)

            when (whatButtonIsPicked.value) {
                TypeOfButtons.Groups -> {

                }

                TypeOfButtons.Teachers -> {
                    getAllTeacherByUserInput(inputOfUser)
                }

                TypeOfButtons.Classroom -> {

                }

                TypeOfButtons.Exams -> {

                }

                null -> {}
            }

        }
    }

    suspend fun getAllTeacherByUserInput(teacherName: String) = withContext(Dispatchers.Main) {
        val listOfTeacherSearchEntity = getAllTeachersByUserInputUseCase.invoke(teacherName)
        when (listOfTeacherSearchEntity) {
            is TRezult.Success -> {
                _teachersList.mValue = listOfTeacherSearchEntity.data
            }

            is TRezult.Error -> {
                _errorEmptyList.mValue = listOfTeacherSearchEntity.exception
                //TODO("Сделать, чтобы когда приходил пустой лист, у нас выводилось, что ничего нет")
                _teachersList.mValue = dataTeacherList
            }
        }
    }

    fun teacherIsPicked(teacherSearchEntity: TeacherSearchEntity) {

    }

    fun installTypeOfSearch(type: TypeOfButtons) {
        _whatButtonIsPicked.mValue = type
    }


    companion object {

        private const val SEARCH_DELAY = 700L


    }

}
