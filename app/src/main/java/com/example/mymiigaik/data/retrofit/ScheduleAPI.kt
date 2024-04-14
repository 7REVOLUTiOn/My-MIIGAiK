package com.example.mymiigaik.data.retrofit

import com.example.myapplication.BuildConfig
import com.example.mymiigaik.data.beans.TeacherBean
import com.example.mymiigaik.data.beans.TeacherScheduleBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleAPI {

    @GET("search/teacher-schedule")
    suspend fun getListOfTeachers(
        @Query("name") name:String
    ):List<TeacherBean>



    @GET("teacher-schedule")
    suspend fun getTeacherScheduleById(
        @Query("id") id:String
    ): TeacherScheduleBean


    companion object{
        private var scheduleAPI: ScheduleAPI? = null

        fun getInstance(): ScheduleAPI {
            if (scheduleAPI == null) {
                val okHttpClient = MyOkHttp(
                    isSafe = !BuildConfig.DEBUG
                ).get()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl("http://192.168.1.52:8080/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                scheduleAPI = retrofit.create(ScheduleAPI::class.java)
            }
            return scheduleAPI!!
        }
    }



}