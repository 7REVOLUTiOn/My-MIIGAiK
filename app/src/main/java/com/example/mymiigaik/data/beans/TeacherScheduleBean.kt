package com.example.mymiigaik.data.beans
import com.google.gson.annotations.SerializedName


data class TeacherScheduleBean(
    @SerializedName("верхняя")
    val top: Top,
    @SerializedName("нижняя")
    val low: Low
) {
    data class Top(
        @SerializedName("Вторник")
        val tuesday: List<Tuesday?>,
        @SerializedName("Понедельник")
        val monday: List<Monday?>,
        @SerializedName("Пятница")
        val friday: List<Friday?>,
        @SerializedName("Среда")
        val wednesday: List<Wednesday?>,
        @SerializedName("Суббота")
        val saturday: List<Saturday?>,
        @SerializedName("Четверг")
        val thursday: List<Thursday?>
    ) {

        data class Tuesday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Monday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )



        data class Friday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Wednesday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Saturday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Thursday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )
    }

    data class Low(
        @SerializedName("Вторник")
        val tuesday: List<Tuesday?>,
        @SerializedName("Понедельник")
        val monday: List<Monday?>,
        @SerializedName("Пятница")
        val friday: List<Friday?>,
        @SerializedName("Среда")
        val wednesday: List<Wednesday?>,
        @SerializedName("Суббота")
        val saturday: List<Saturday?>,
        @SerializedName("Четверг")
        val thursday: List<Thursday?>
    ) {
        data class Tuesday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Monday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )



        data class Friday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Wednesday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Saturday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )

        data class Thursday(
            @SerializedName("additionalInfo")
            val additionalInfo: String?,
            @SerializedName("classroom")
            val classroom: String?,
            @SerializedName("day")
            val day: String?,
            @SerializedName("group")
            val group: String?,
            @SerializedName("lesson")
            val lesson: String?,
            @SerializedName("lessonType")
            val lessonType: String?,
            @SerializedName("subject")
            val subject: String?,
            @SerializedName("weekType")
            val weekType: String?
        )
    }
}