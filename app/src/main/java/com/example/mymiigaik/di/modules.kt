package com.example.mymiigaik.di

import com.example.mymiigaik.data.implimentations.GetTeachersFromRemoteRepositoryImpl
import com.example.mymiigaik.data.mappers.mappersForSchedule.TeactherBeanToTeacherSeachEntity
import com.example.mymiigaik.data.retrofit.ScheduleAPI
import com.example.mymiigaik.domain.implemantations.GetListOfTeachersInteractorImpl
import com.example.mymiigaik.data.IGetTeachersFromRemoteRepository
import com.example.mymiigaik.domain.interfaces.IGetListOfTeacherInteractor
import com.example.mymiigaik.ui.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositories = module {


    factory<ScheduleAPI> { ScheduleAPI.getInstance() }

    factory <IGetTeachersFromRemoteRepository>{
        val api = get<ScheduleAPI>()
        val beanMapper = TeactherBeanToTeacherSeachEntity()
        GetTeachersFromRemoteRepositoryImpl(
            getTeachersFromRemoteRepositoryAPI = api::getListOfTeachers,
            teacherBeanToTeacherSearchEntityMapper =  beanMapper.teacherBeanItem
        )
    }

    factory<IGetListOfTeacherInteractor> {
        val remoteRep = get<IGetTeachersFromRemoteRepository>()

        GetListOfTeachersInteractorImpl(
            getListOfTeachersFromRemoteRepUseCase = remoteRep::getTeachersFromRemoteRepositoryImpl
        )
    }



    viewModel<ScheduleViewModel> {
        val getAllTeachersByUserInputUseCase = get<IGetListOfTeacherInteractor>()
        ScheduleViewModel(
            getAllTeachersByUserInputUseCase = getAllTeachersByUserInputUseCase::getTeachersFromRemoteRepositoryImpl
        )
    }

}