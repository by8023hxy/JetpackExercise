package com.baiyu.basearchitecture.base

import androidx.lifecycle.*
import com.baiyu.basearchitecture.network.AppException
import com.baiyu.basearchitecture.network.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
open class BaseCoroutinesViewModel : ViewModel() {

    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    inline fun <T> launchOnFlow(crossinline request: suspend () -> BaseResponse<T>): Flow<T> {
        return flow {
            val response = request()
            when (response.getResponseCode()) {
                0 -> {
                    emit(response.getResponseData())
                }
                else -> {
                    throw AppException(response.getResponseMsg(), response.getResponseCode())
                }
            }
        }.flowOn(viewModelScope.coroutineContext + Dispatchers.IO)
    }
}