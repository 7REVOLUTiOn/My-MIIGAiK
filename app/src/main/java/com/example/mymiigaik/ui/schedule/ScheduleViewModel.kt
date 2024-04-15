package com.example.mymiigaik.ui.schedule

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymiigaik.domain.entities.scheduleEntitise.classromEntities.ClassroomSearchEntities
import com.example.mymiigaik.domain.entities.scheduleEntitise.lessonsEntites.GroupSearchEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.SchedulerEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.TeacherSearchEntity
import com.example.mymiigaik.ui.schedule.model.DayModel
import com.example.mymiigaik.ui.schedule.model.IShedulerModel
import com.example.mymiigaik.ui.schedule.model.LessonModel
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
    private val getAllTeachersByUserInputUseCase: suspend (name: String) -> TRezult<List<TeacherSearchEntity>>, // Запрос на получение листа имен преподавателей
    private val getTeacherScheduleByPickedUseCase: suspend (id:String) -> TRezult<SchedulerEntity>,
    private val getAllGroupsByUserInputUseCase: suspend (name:String) -> TRezult<List<GroupSearchEntity>>,
    private val getAllClassroomsByUserInputUseCase: suspend (name:String) -> TRezult<List<ClassroomSearchEntities>>
) : ViewModel() {


    private val _teachersList = MutableLiveData<List<TeacherSearchEntity>>()
    val teachersList = _teachersList.asLiveData()

    private val _scheduler = MutableLiveData<MutableList<IShedulerModel>>()
    val scheduler = _scheduler.asLiveData()

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


    suspend fun


    suspend fun getAllTeacherByUserInput(teacherName: String) = withContext(Dispatchers.Main) {
        Log.d("Поиск","Начало поиска")
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

     fun teacherIsPicked(id: String) {
        searchJob?.cancel()
        _teachersList.mValue = dataTeacherList
         viewModelScope.launch {
            getScheduleOfTeacher(id)
         }
    }

    suspend fun getScheduleOfTeacher(teacherId:String) = withContext(Dispatchers.Main){
        val shedulerOfTeacher = getTeacherScheduleByPickedUseCase.invoke(teacherId)
        when(shedulerOfTeacher){
            is TRezult.Success -> {
                Log.d("Cool","${shedulerOfTeacher.data}")
                val schedulerListModel: MutableList<IShedulerModel> = mutableListOf()

                shedulerOfTeacher.data.topWeek.days.forEach {
                    schedulerListModel.add(DayModel(it.dayOfWeek.toString()))
                    it.lessons.forEach {
                        schedulerListModel.add(LessonModel(
                            additionalInfo = it.additionalInfo,
                            classroom = it.classroom,
                            day = it.day,
                            group = it.group,
                            lesson = it.lesson,
                            lessonType = it.lessonType,
                            subject = it.subject,
                            weekType = it.weekType
                        ))
                    }
                }

                Log.d("Прием,","${schedulerListModel}")

                _scheduler.mValue = schedulerListModel



            }
            is TRezult.Error -> {
                Log.d("Cool","Пизда")
                _errorEmptyList.mValue = shedulerOfTeacher.exception
            }
        }

    }






    fun installTypeOfSearch(type: TypeOfButtons) {
        _whatButtonIsPicked.mValue = type
    }


    companion object {

        private const val SEARCH_DELAY = 700L

    }

}
