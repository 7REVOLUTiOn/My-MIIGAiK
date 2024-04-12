package com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities

data class ScheduleWeekEntity(
    val days: List<ScheduleOfDayEntity>,
    val typeOfWeek: WeekTypes
)
