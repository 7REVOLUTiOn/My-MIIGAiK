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

    fun getAllTeachersByName(teacherName: String) {
        viewModelScope.launch {
            getAllTeacherByUserInput(teacherName)
        }
    }

    suspend fun getAllTeacherByUserInput(teacherName: String) = withContext(Dispatchers.Main) {
        Log.d("АУЕ", "Запросили список")
        val listOfTeacherSearchEntity = getAllTeachersByUserInputUseCase.invoke(teacherName)
        when (listOfTeacherSearchEntity) {
            is TRezult.Success -> {
                _teachersList.mValue = listOfTeacherSearchEntity.data
            }

            is TRezult.Error -> {
                _error.mValue = listOfTeacherSearchEntity.exception
            }
        }
    }


}