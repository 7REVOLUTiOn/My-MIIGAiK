package com.example.mymiigaik.data.implimentations

import android.util.Log
import com.example.mymiigaik.data.beans.TeacherBean
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.TeacherSearchEntity
import com.example.mymiigaik.data.ITeacherRemoteRepository
import com.example.mymiigaik.data.beans.TeacherScheduleBean
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.SchedulerEntity
import com.example.mymiigaik.utils.SearchException
import com.example.mymiigaik.utils.TRezult
import com.example.mymiigaik.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TeachersRemoteRepository(
    private val getTeachersFromRemoteRepositoryAPI: suspend (name:String) -> List<TeacherBean>,
    private val getTeacherScheduleBeanFromRemoteRepositoryAPI: suspend (id:String) -> TeacherScheduleBean,
    private val teacherBeanToTeacherSearchEntityMapper: (teacherBeanItem: TeacherBean) -> TeacherSearchEntity?,
    private val teacherScheduleBeanToTeacherSchedule: (TeacherScheduleBean) -> SchedulerEntity?
): ITeacherRemoteRepository {


    override suspend fun getTeachersFromRemoteRepositoryImpl(name: String): TRezult<List<TeacherSearchEntity>> =
        withContext(Dispatchers.IO){
            return@withContext runCatching {
                Log.d("Поиск","Запрос к API")
                val listOfTeachersFromRemoteRepository = getTeachersFromRemoteRepositoryAPI.invoke(name)

                Log.d("Поиск","${listOfTeachersFromRemoteRepository.get(0)}")
                val listOfTRezultTeachers = listOfTeachersFromRemoteRepository.mapNotNull {
                    teacherBeanToTeacherSearchEntityMapper(it)
                }
                if (listOfTRezultTeachers.isEmpty()){
                    throw SearchException.EmptyList(Exception())
                }

                TRezult.Success(listOfTRezultTeachers)
            }.getOrElse {
                it.logError()
                TRezult.Error(SearchException.get(it))
            }
        }

    override suspend fun getTeacherScheduleFromRemoteRepository(id: String): TRezult<SchedulerEntity>  =
        withContext(Dispatchers.IO){
            return@withContext runCatching {

                Log.d("Cool","Отправили запрос ${id}")
                val scheduleOfTeacherBean = getTeacherScheduleBeanFromRemoteRepositoryAPI.invoke(id)
                Log.d("Cool","Бины: ${scheduleOfTeacherBean.low}, ${scheduleOfTeacherBean.top}")
                val schedulerEntity = teacherScheduleBeanToTeacherSchedule(scheduleOfTeacherBean)
                if (schedulerEntity != null ){
                    Log.d("Cool","${schedulerEntity}")
                    TRezult.Success(schedulerEntity)
                }
               else {
                    throw SearchException.EmptyList(Exception())
                }

            }.getOrElse {
                TRezult.Error(SearchException.get(it))
            }
        }
}