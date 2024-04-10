package com.example.mymiigaik.domain.implemantations

import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.data.IGetTeachersFromRemoteRepository
import com.example.mymiigaik.domain.interfaces.IGetListOfTeacherInteractor
import com.example.mymiigaik.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetListOfTeachersInteractorImpl(
    private val getListOfTeachersFromRemoteRepUseCase: suspend (name:String) -> TRezult<List<TeacherSearchEntity>>
): IGetListOfTeacherInteractor {

    override suspend fun getTeachersFromRemoteRepositoryImpl(name: String): TRezult<List<TeacherSearchEntity>> =
        withContext(Dispatchers.IO){

            val listOfTeachersInteractorImpl = getListOfTeachersFromRemoteRepUseCase.invoke(name)
            return@withContext listOfTeachersInteractorImpl
        }
}