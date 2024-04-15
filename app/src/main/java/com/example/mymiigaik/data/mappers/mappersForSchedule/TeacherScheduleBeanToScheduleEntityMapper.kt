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
        val scheduleOfПонедельник = ScheduleOfDayEntity(listLessonOfMonday,DaysOfWeek.Понедельник)

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
        val scheduleOfВторник = ScheduleOfDayEntity(listLessonOfTuesday,DaysOfWeek.Вторник)

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
        val scheduleOfСреда = ScheduleOfDayEntity(listLessonOfWednesday,DaysOfWeek.Среда)

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
        val scheduleOfThisday = ScheduleOfDayEntity(listLessonOfThursday,DaysOfWeek.Чертверг)

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
        val scheduleOfПятница = ScheduleOfDayEntity(listLessonOfFriday,DaysOfWeek.Пятница)

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
        val scheduleOfСуббота = ScheduleOfDayEntity(listLessonOfSaturday,DaysOfWeek.Суббота)

        val topSchedule = ScheduleWeekEntity(listOf(scheduleOfПонедельник,scheduleOfВторник,scheduleOfСреда,scheduleOfThisday,scheduleOfПятница,scheduleOfСуббота),WeekTypes.Top)


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
        val scheduleOfПонедельникLow = ScheduleOfDayEntity(listLessonOfMondayLow,DaysOfWeek.Понедельник)

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
        val scheduleOfВторникLow = ScheduleOfDayEntity(listLessonOfTuesdayLow,DaysOfWeek.Вторник)

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
        val scheduleOfWednesdayLow = ScheduleOfDayEntity(listLessonOfWednesdayLow,DaysOfWeek.Чертверг)

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
        val scheduleOfThisdayLow = ScheduleOfDayEntity(listLessonOfThursdayLow,DaysOfWeek.Среда)

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
        val scheduleOfПятницаLow = ScheduleOfDayEntity(listLessonOfFridayLow,DaysOfWeek.Пятница)

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
        val scheduleOfСубботаLow = ScheduleOfDayEntity(listLessonOfSaturdayLow,DaysOfWeek.Суббота)

        val ScheduleLow = ScheduleWeekEntity(listOf(scheduleOfПонедельникLow,scheduleOfВторникLow,scheduleOfWednesdayLow,scheduleOfThisdayLow,scheduleOfПятницаLow,scheduleOfСубботаLow),WeekTypes.Low)


       SchedulerEntity(topSchedule, ScheduleLow)




    }.getOrElse {
        it.logError()
        null
    }



}