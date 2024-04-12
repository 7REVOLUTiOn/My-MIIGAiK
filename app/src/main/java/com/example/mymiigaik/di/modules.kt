package com.example.mymiigaik.di

import com.example.mymiigaik.data.implimentations.TeachersRemoteRepository
import com.example.mymiigaik.data.mappers.mappersForSchedule.TeactherBeanToTeacherSeachEntity
import com.example.mymiigaik.data.retrofit.ScheduleAPI
import com.example.mymiigaik.domain.implemantations.GetListOfTeachersInteractorImpl
import com.example.mymiigaik.data.ITeacherRemoteRepository
import com.example.mymiigaik.data.mappers.mappersForSchedule.TeacherScheduleBeanToScheduleEntityMapper
import com.example.mymiigaik.domain.interfaces.IGetListOfTeacherInteractor
import com.example.mymiigaik.ui.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositories = module {


    factory<ScheduleAPI> { ScheduleAPI.getInstance() }

    factory <ITeacherRemoteRepository>{
        val api = get<ScheduleAPI>()
        val beanMapper = TeactherBeanToTeacherSeachEntity()
        val beanScheduleToEntity = TeacherScheduleBeanToScheduleEntityMapper()
        TeachersRemoteRepository(
            getTeachersFromRemoteRepositoryAPI = api::getListOfTeachers,
            getTeacherScheduleBeanFromRemoteRepositoryAPI = api::getTeacherScheduleById,
            teacherBeanToTeacherSearchEntityMapper =  beanMapper.teacherBeanItem,
            teacherScheduleBeanToTeacherSchedule = beanScheduleToEntity.teacherBeanItem
        )
    }

    factory<IGetListOfTeacherInteractor> {
        val remoteRep = get<ITeacherRemoteRepository>()

        GetListOfTeachersInteractorImpl(
            getListOfTeachersFromRemoteRepUseCase = remoteRep::getTeachersFromRemoteRepositoryImpl
        )
    }



    viewModel<ScheduleViewModel> {
        val getAllTeachersByUserInputUseCase = get<IGetListOfTeacherInteractor>()
        val getTeacherSchedule = get<ITeacherRemoteRepository>()
        ScheduleViewModel(
            getAllTeachersByUserInputUseCase = getAllTeachersByUserInputUseCase::getTeachersFromRemoteRepositoryImpl,
            getTeacherScheduleByPicked = getTeacherSchedule::getTeacherScheduleFromRemoteRepository
        )
    }

}