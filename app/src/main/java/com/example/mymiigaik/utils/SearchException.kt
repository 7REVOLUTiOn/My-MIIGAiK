package com.example.mymiigaik.utils

import java.net.ConnectException
import java.net.UnknownHostException

sealed class SearchException(exception: Throwable) : Throwable(exception) {
    class Other(exception: Throwable) : SearchException(exception)

    class NoInternet(exception: Throwable) : SearchException(exception)

    class EmptyList(exception: Throwable): SearchException(exception)

    override val cause: Throwable = exception //domain

    companion object { //data
        fun get(exception: Throwable): SearchException {
            exception.logError()
            return when (exception) {
                is SearchException -> exception
                is UnknownHostException,
                is ConnectException -> NoInternet(exception)
                //Дополнять
                else -> Other(exception)
            }
        }
    }
}