package com.baiyu.basearchitecture.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.observe
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baiyu.basearchitecture.event.RequestState
import java.lang.Exception

typealias StatefulLiveData<T> = LiveData<RequestState<T>>
typealias StatefulMutableLiveData<T> = MutableLiveData<RequestState<T>>

@MainThread
inline fun <T> StatefulLiveData<T>.observeState(
    owner: LifecycleOwner,
    crossinline onLoading: () -> Unit = {},
    crossinline onSuccess: (T?) -> Unit = {},
    crossinline onError: (Exception?) -> Unit = {}
) {
    observe(owner) { state ->
        when (state) {
            is RequestState.Loading -> onLoading.invoke()
            is RequestState.Success -> onSuccess(state.data)
            is RequestState.Error -> onError(state.error)
        }
    }
}