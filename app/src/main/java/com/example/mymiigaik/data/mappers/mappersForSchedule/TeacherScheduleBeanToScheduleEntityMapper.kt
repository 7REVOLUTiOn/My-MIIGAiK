package com.example.mymiigaik.data.mappers.mappersForSchedule

import com.example.mymiigaik.data.beans.TeacherScheduleBean
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.DaysOfWeek
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.LessonEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.ScheduleOfDayEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.ScheduleWeekEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.SchedulerEntity
import com.example.mymiigaik.domain.entities.scheduleEntitise.teacherEntities.WeekTypes
import com.example.mymiigaik.utils.logError

class TeacherScheduleBeanToScheduleEntityMapper {


    val teacherBeanItem: (TeacherScheduleBean) -> SchedulerEntity? = {it.toEntity()}

    private fun TeacherScheduleBean.toEntity() = runCatching {
        val listLessonOfMonday: List<LessonEntity> = top.monday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfMonday = ScheduleOfDayEntity(listLessonOfMonday,DaysOfWeek.Monday)

        val listLessonOfTuesday: List<LessonEntity> = top.tuesday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfTuesday = ScheduleOfDayEntity(listLessonOfTuesday,DaysOfWeek.Tuesday)

        val listLessonOfWednesday: List<LessonEntity> = top.wednesday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfWednesday = ScheduleOfDayEntity(listLessonOfWednesday,DaysOfWeek.Wednesday)

        val listLessonOfThursday: List<LessonEntity> = top.thursday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfThisday = ScheduleOfDayEntity(listLessonOfThursday,DaysOfWeek.Thursday)

        val listLessonOfFriday: List<LessonEntity> = top.friday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfFriday = ScheduleOfDayEntity(listLessonOfFriday,DaysOfWeek.Friday)

        val listLessonOfSaturday: List<LessonEntity> = top.saturday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfSaturday = ScheduleOfDayEntity(listLessonOfSaturday,DaysOfWeek.Saturday)

        val topSchedule = ScheduleWeekEntity(listOf(scheduleOfMonday,scheduleOfTuesday,scheduleOfWednesday,scheduleOfThisday,scheduleOfFriday,scheduleOfSaturday),WeekTypes.Top)


        ////////////////////////////////////

        val listLessonOfMondayLow: List<LessonEntity> = low.monday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfMondayLow = ScheduleOfDayEntity(listLessonOfMondayLow,DaysOfWeek.Monday)

        val listLessonOfTuesdayLow: List<LessonEntity> = low.tuesday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfTuesdayLow = ScheduleOfDayEntity(listLessonOfTuesdayLow,DaysOfWeek.Tuesday)

        val listLessonOfWednesdayLow: List<LessonEntity> = low.wednesday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfWednesdayLow = ScheduleOfDayEntity(listLessonOfWednesdayLow,DaysOfWeek.Thursday)

        val listLessonOfThursdayLow: List<LessonEntity> = low.thursday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfThisdayLow = ScheduleOfDayEntity(listLessonOfThursdayLow,DaysOfWeek.Wednesday)

        val listLessonOfFridayLow: List<LessonEntity> = low.friday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfFridayLow = ScheduleOfDayEntity(listLessonOfFridayLow,DaysOfWeek.Friday)

        val listLessonOfSaturdayLow: List<LessonEntity> = low.saturday.mapNotNull {
            LessonEntity(
                additionalInfo = it?.additionalInfo,
                classroom = it?.classroom,
                day = it?.day,
                group = it?.group,
                lesson = it?.lesson,
                lessonType = it?.lessonType,
                subject = it?.subject,
                weekType = it?.weekType
            )
        }
        val scheduleOfSaturdayLow = ScheduleOfDayEntity(listLessonOfSaturdayLow,DaysOfWeek.Saturday)

        val ScheduleLow = ScheduleWeekEntity(listOf(scheduleOfMondayLow,scheduleOfTuesdayLow,scheduleOfWednesdayLow,scheduleOfThisdayLow,scheduleOfFridayLow,scheduleOfSaturdayLow),WeekTypes.Low)


       SchedulerEntity(topSchedule, ScheduleLow)




    }.getOrElse {
        it.logError()
        null
    }



}