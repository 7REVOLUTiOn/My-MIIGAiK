package com.example.mymiigaik.domain.implemantations

import android.util.Log
import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.domain.interfaces.IGetTeachersFromRemoteRepository
import com.example.mymiigaik.utils.TRezult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetListOfTeachersInteractorImpl(
    private val getListOfTeachersFromRemoteRepUseCase: suspend (name:String) -> TRezult<List<TeacherSearchEntity>>
): IGetTeachersFromRemoteRepository {

    override suspend fun getTeachersFromRemoteRepositoryImpl(name: String): TRezult<List<TeacherSearchEntity>> =
        withContext(Dispatchers.IO){
            Log.d("АУЕ","Запросили список из doamain слоя")
            val listOfTeachersInteractorImpl = getListOfTeachersFromRemoteRepUseCase.invoke(name)
            Log.d("АУЕ","${listOfTeachersInteractorImpl.data?.get(0)}")
            return@withContext listOfTeachersInteractorImpl
        }
}