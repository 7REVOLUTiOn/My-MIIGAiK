package com.example.mymiigaik.utils

sealed class TRezult<T>(
    open val data: T? = null,
    open val exception: SearchException? = null
) {
    data class Success<T>(override val data: T) : TRezult<T>()
    data class Error<T>(override val exception: SearchException) :
        TRezult<T>()
}