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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleViewModel(
    private val getAllTeachersByUserInputUseCase: suspend (name: String) -> TRezult<List<TeacherSearchEntity>> // Запрос на получение листа имен преподавателей
) : ViewModel() {


    private val _teachersList = MutableLiveData<List<TeacherSearchEntity>>()
    val teachersList = _teachersList.asLiveData()

    private val _error = MutableLiveData<SearchException>()
    val error = _error.asLiveData()

    private val dataTeacherList = mutableListOf<TeacherSearchEntity>()


    private val _whatButtonIsPicked = MutableLiveData<TypeOfButtons>()
    val whatButtonIsPicked = _whatButtonIsPicked.asLiveData()


    fun setSearchText(searchText:String){
        if (searchText.isEmpty()){
            _teachersList.mValue = dataTeacherList
        } else {
            startTheSearch(searchText)
        }
    }

    fun startTheSearch(inputOfUser: String) {
        viewModelScope.launch {
            if (inputOfUser.isNotEmpty()) {
                when (whatButtonIsPicked.value) {
                    TypeOfButtons.Groups -> {

                    }

                    TypeOfButtons.Teachers -> {
                        getAllTeacherByUserInput(inputOfUser)
                    }

                    TypeOfButtons.Classroom -> {

                    }

                    TypeOfButtons.Classroom -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    suspend fun getAllTeacherByUserInput(teacherName: String) = withContext(Dispatchers.Main) {
        Log.d("АУЕ", "Запросили список")
        val listOfTeacherSearchEntity = getAllTeachersByUserInputUseCase.invoke(teacherName)
        when (listOfTeacherSearchEntity) {
            is TRezult.Success -> {
                Log.d("AYE","${listOfTeacherSearchEntity.data}")
                _teachersList.mValue = listOfTeacherSearchEntity.data
                Log.d("АУЕ", "${listOfTeacherSearchEntity.data}")
                //TODO("Сделать, чтобы когда приходил пустой лист, у нас выводилось, что ничего нет")
            }

            is TRezult.Error -> {
                _error.mValue = listOfTeacherSearchEntity.exception

            }
        }
    }

    fun teacherIsPicked(teacherSearchEntity: TeacherSearchEntity) {

    }

    fun installTypeOfSearch(type: TypeOfButtons) {
        _whatButtonIsPicked.mValue = type
    }

}