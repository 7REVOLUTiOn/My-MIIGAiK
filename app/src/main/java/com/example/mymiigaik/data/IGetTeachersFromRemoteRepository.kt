package com.example.mymiigaik.data

import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.utils.TRezult

interface IGetTeachersFromRemoteRepository {

    suspend fun getTeachersFromRemoteRepositoryImpl(name:String): TRezult<List<TeacherSearchEntity>>

}