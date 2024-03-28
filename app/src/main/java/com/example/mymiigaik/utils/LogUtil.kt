package com.example.mymiigaik.utils

import android.util.Log
import com.example.myapplication.BuildConfig
import java.util.UUID


import java.util.*

fun debugDo(isWork: Boolean = true, foo: () -> Unit) {
    if (isWork && BuildConfig.DEBUG) {
        foo()
    }
}


fun logInfo(txt: String, part: Int = 2) {
    if (BuildConfig.DEBUG) {
        val tag = "!!!"
        val cti = runCatching { Throwable().stackTrace[part] }.getOrNull()
        val str =
            "${cti?.methodName} / (${cti?.fileName}:${cti?.lineNumber}) : ${txt}\n${UUID.randomUUID()}"
        runCatching {
            Log.e(tag, str)
        }.getOrElse {
            System.out.println("${tag}: ${str}")
        }
    }
}


fun Throwable.logError(part: Int = 3, errorPart: Int = 0) {
    if (BuildConfig.DEBUG) {
        val error = runCatching { this.stackTrace[errorPart] }.getOrNull()
        logInfo(
            "ERROR: ${this.javaClass.canonicalName}: ${this.message} \n ${error}",
            part
        )

        val tag = "!!!"
        val str = this.stackTraceToString()
        runCatching {
            Log.i(tag, str)
        }.getOrElse {
            System.out.println("$tag: $str")
        }
    }
}

fun logUtilTest() {
    runCatching {
        10 / 0
    }.getOrElse {
        logInfo("test1")

        debugDo(false) {
            logInfo("test2")
        }

        it.logError()
    }
}
