package com.baiyu.basearchitecture.event

/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
sealed class RequestState<out T> {
    object Loading : RequestState<Nothing>()
    data class Success<out T>(val data: T?) : RequestState<T>()
    data class Error(val code: String? = null, val error: Exception? = null) :
        RequestState<Nothing>()
}

