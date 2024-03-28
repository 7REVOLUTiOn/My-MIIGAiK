package com.example.mymiigaik.di

import com.example.mymiigaik.data.implimentations.GetTeachersFromRemoteRepositoryImpl
import com.example.mymiigaik.data.retrofit.ScheduleAPI
import com.example.mymiigaik.domain.interfaces.IGetTeachersFromRemoteRepository
import com.example.mymiigaik.ui.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositories = module {


    factory<ScheduleAPI> { ScheduleAPI.getInstance() }

    factory <IGetTeachersFromRemoteRepository>{
        val api = get<ScheduleAPI>()
        GetTeachersFromRemoteRepositoryImpl(
            getTeachersFromRemoteRepositoryAPI = api::getListOfTeachers
        )
    }


    viewModel<ScheduleViewModel> {
        val getAllTeachersByUserInputUseCase = get<IGetTeachersFromRemoteRepository>()
        ScheduleViewModel(
            getAllTeachersByUserInputUseCase = getAllTeachersByUserInputUseCase::getTeachersFromRemoteRepositoryImpl
        )
    }

}