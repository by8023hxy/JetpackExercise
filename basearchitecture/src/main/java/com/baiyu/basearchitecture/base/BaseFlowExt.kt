package com.baiyu.basearchitecture.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.baiyu.basearchitecture.extensions.logd
import com.baiyu.basearchitecture.extensions.loge
import com.baiyu.basearchitecture.network.AppException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author:BaiYu
 * @Time:2020/7/27 9:12
 * @description：Flow
 */

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> BaseCoroutinesViewModel.flowMergeRequest(
    flow: Flow<Flow<T>>,
    success: (T) -> Unit,
    failure: (AppException) -> Unit = {}
) {
    viewModelScope.launch {
        flow.flattenMerge()
            .onStart {
                "flowMergeRequest onStart".loge("BaseAppExt_MergeRequest")
            }.onCompletion {
                "flowMergeRequest onCompletion".loge("BaseAppExt_MergeRequest")
            }.catch {
                failure(getApiException(it))
                "flowMergeRequest catch apiException ${getApiException(it).errorMsg}".loge("BaseAppExt_MergeRequest")
            }.collect {
                success(it)
            }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> flowLiveData(
    request: suspend () -> Flow<T>
): LiveData<T> {
    return liveData {
        request().onStart {
            "flowLiveData onStart".loge("BaseAppExt_LiveData")
        }.onCompletion {
            "flowLiveData onCompletion".loge("BaseAppExt_LiveData")
        }.catch {
            "flowLiveData catch apiException ${getApiException(it).errorMsg}".loge("BaseAppExt_LiveData")
        }.collectLatest {
            emit(it)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> BaseCoroutinesViewModel.flowRequestVm(
    request: suspend () -> Flow<T>,
    success: (T) -> Unit,
    failure: (AppException) -> Unit = {}
) {
    viewModelScope.launch {
        request().onStart {
            "flowRequestVm onStart".logd("BaseAppExt_RequestVm")
        }.onCompletion {
            "flowRequestVm onCompletion".logd("BaseAppExt_RequestVm")
        }.catch {
            failure(getApiException(it))
            "flowRequestVm catch apiException ${getApiException(it).errorMsg}".loge("BaseAppExt_RequestVm")
            "flowRequestVm catch=$it".loge("BaseAppExt_RequestVm")
        }.collectLatest {
            success(it)
        }
    }
}

//关联数据失败，显示加载失败View的请求方式
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> BaseCoroutinesViewModel.flowRelatedUI(
    request: suspend () -> Flow<T>,
    success: (T) -> Unit,
    failure: (AppException) -> Unit = {}
) {
    viewModelScope.launch {
        request().onStart {
            "flowRelatedUI onStart".logd()
        }.onCompletion {
            "flowRelatedUI onCompletion".logd()
        }.catch {
            failure(getApiException(it))
            "flowRelatedUI catch apiException ${getApiException(it).errorMsg}".loge()
            "flowRelatedUI catch=$it".loge()
        }.collectLatest {
            success(it)
        }
    }
}


@OptIn(ExperimentalCoroutinesApi::class)
fun <T> BaseCoroutinesViewModel.flowRequestNoCheck(
    request: suspend () -> Flow<T>,
    success: (T) -> Unit,
    failure: (AppException) -> Unit = {}
) {
    viewModelScope.launch {
        request().onStart {
            "flowRequestNoCheck onStart".logd()
        }.onCompletion {
            "flowRequestNoCheck onCompletion".logd()
        }.catch {
            failure(getApiException(it))
            "flowRequestNoCheck catch ${getApiException(it).errorMsg}".loge()
        }.collectLatest {
            success(it)
        }
    }
}

/**
 * 捕获异常信息
 */
internal fun getApiException(e: Throwable): AppException {
    return when (e) {
        is UnknownHostException -> {
            AppException("网络异常", -100)
        }
        is JSONException -> {//|| e is JsonParseException
            AppException("数据异常", -100)
        }
        is SocketTimeoutException -> {
            AppException("连接超时", -100)
        }
        is ConnectException -> {
            AppException("连接错误", -100)
        }
        is HttpException -> {
            AppException("http code ${e.code()}", -100)
        }
        is AppException -> {
            e
        }
        /**
         * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
         * 强行结束协程，与java中InterruptException类似，所以不必理会
         */
        is CancellationException -> {
            AppException("CancellationException", -10)
        }
        else -> {
            AppException("未知错误", -100)
        }
    }
}