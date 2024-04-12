package com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities

data class ScheduleOfDayEntity(
   val lessons: List<LessonEntity>,
    val dayOfWeek: DaysOfWeek
)
