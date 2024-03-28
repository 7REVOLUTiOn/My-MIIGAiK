package com.example.mymiigaik.data.mappers.mappersForSchedule

import com.example.mymiigaik.data.beans.TeacherBean
import com.example.mymiigaik.domain.entities.scheduleEntitise.TeacherSearchEntity
import com.example.mymiigaik.utils.logError

class TeactherBeanToTeacherSeachEntity {

    val teacherBeanItem: (data: TeacherBean) -> TeacherSearchEntity? = {it.toEntity()}

    private fun TeacherBean.toEntity() = runCatching {
        TeacherSearchEntity(
            name = teacherName,
            scheduleLink = scheduleLink
        )
    }.getOrElse {
        it.logError()
        null
    }

}