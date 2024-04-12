package com.example.mymiigaik.domain.interfaces

import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.TeacherSearchEntity
import com.example.mymiigaik.utils.TRezult

interface IGetListOfTeacherInteractor {


    suspend fun getTeachersFromRemoteRepositoryImpl(name: String): TRezult<List<TeacherSearchEntity>>

}