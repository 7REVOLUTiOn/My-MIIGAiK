package com.example.mymiigaik.data.implimentations

import com.example.mymiigaik.data.beans.TeacherBean
import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.data.IGetTeachersFromRemoteRepository
import com.example.mymiigaik.utils.SearchException
import com.example.mymiigaik.utils.TRezult
import com.example.mymiigaik.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTeachersFromRemoteRepositoryImpl(
    private val getTeachersFromRemoteRepositoryAPI: suspend (name:String) -> List<TeacherBean>,
    private val teacherBeanToTeacherSearchEntityMapper: (teacherBeanItem: TeacherBean) -> TeacherSearchEntity?
): IGetTeachersFromRemoteRepository {


    override suspend fun getTeachersFromRemoteRepositoryImpl(name: String): TRezult<List<TeacherSearchEntity>> =
        withContext(Dispatchers.IO){
            return@withContext runCatching {
                val listOfTeachersFromRemoteRepository = getTeachersFromRemoteRepositoryAPI.invoke(name)


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

}