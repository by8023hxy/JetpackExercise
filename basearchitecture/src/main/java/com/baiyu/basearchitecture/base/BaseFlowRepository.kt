package com.baiyu.basearchitecture.base


import com.baiyu.basearchitecture.network.AppException
import com.baiyu.basearchitecture.network.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @Author:BaiYu
 * @Time:2020/7/27 9:20
 * @description：Flow Repo
 */

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> flowRequest(request: suspend () -> BaseResponse<T>): Flow<T> {
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
    }.flowOn(Dispatchers.IO)
}


fun <T> flowRequestNoCheck(request: suspend () -> T): Flow<T> {
    return flow {
        emit(request())
    }.flowOn(Dispatchers.IO)
}