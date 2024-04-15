package com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities

import com.example.mymiigaik.domain.entities.scheduleEntitise.WeekTypes

data class ScheduleWeekEntity(
    val days: List<ScheduleOfDayEntity>,
    val typeOfWeek: WeekTypes
)
