package com.example.mymiigaik.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object LiveDataUtils {

    fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> = this

    var <T>MutableLiveData<T>.mValue:T
        @Deprecated("")
        get() = value!!
        set(newValue) {
            value = newValue
        }

    val Fragment.liveDataOwner: LifecycleOwner
        get() = viewLifecycleOwner

    val FragmentActivity.liveDataOwner: LifecycleOwner
        get() = this

    fun <T> MutableLiveData<T>.setIfNewValue(newValue:T){
        if (newValue != this.value){
            this.mValue = newValue
        }
    }

}