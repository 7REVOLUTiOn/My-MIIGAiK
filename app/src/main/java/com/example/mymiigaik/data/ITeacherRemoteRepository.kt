package com.example.mymiigaik.data

import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.ScheduleOfDayEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.SchedulerEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.TeacherSearchEntity
import com.example.mymiigaik.utils.TRezult

interface ITeacherRemoteRepository {

    suspend fun getTeachersFromRemoteRepositoryImpl(name:String): TRezult<List<TeacherSearchEntity>>

    suspend fun getTeacherScheduleFromRemoteRepository(id:String): TRezult<SchedulerEntity>

}