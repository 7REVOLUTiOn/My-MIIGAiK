package com.example.mymiigaik.data.beans

data class para(
    val a:String,
    val b:String,
    val c:String,
)

data class ScheduleOfDay(
   val pari: List<para>
    )

data class ScheduleWeek(
    val monday: ScheduleOfDay,
    val tyesday: ScheduleOfDay
)

data class Scheduler(
    val firstWeek: ScheduleWeek,
    val secondWeek: ScheduleWeek
)


data class paraEntity(
    val a:String,
    val b:String,
    val c:String,
)


data class ScheduleOfDayEntity(
    val pari:List<paraEntity>,

    val dayOfWeek: DayOfWeek, // день недели
    )

data class ScheduleWeekEntity(
    val days: List<ScheduleOfDayEntity>,
    val type : weekTypes
)

data class SchedulerEntity(
    val firstWeek: List<ScheduleWeekEntity>
)


enum class weekTypes(){
    Ввернхняя, Нижняя
}



enum class DayOfWeek(
    monday,tueda
)