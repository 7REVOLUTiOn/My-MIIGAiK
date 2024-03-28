package com.example.mymiigaik.data.beans

import com.google.gson.annotations.SerializedName

data class TeacherBean(
    @SerializedName("teacherName") val teacherName: String,
    @SerializedName("scheduleTeacher") val scheduleLink:String
)
