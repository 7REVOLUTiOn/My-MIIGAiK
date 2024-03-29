package com.example.mymiigaik.data.beans

import com.google.gson.annotations.SerializedName

data class TeacherBean(
    @SerializedName("name") val teacherName: String,
    @SerializedName("scheduleLink") val scheduleLink:String
)
