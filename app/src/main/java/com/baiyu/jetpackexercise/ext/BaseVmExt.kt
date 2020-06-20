package com.baiyu.jetpackexercise.ext

import androidx.lifecycle.viewModelScope
import com.baiyu.basearchitecture.base.BaseCoroutinesViewModel
import com.baiyu.basearchitecture.event.RequestState
import com.baiyu.basearchitecture.livedata.StatefulMutableLiveData
import com.baiyu.basearchitecture.network.AppException
import com.baiyu.basearchitecture.network.BaseResponse
import com.baiyu.basearchitecture.network.ExceptionHandle
import com.baiyu.jetpackexercise.base.BaseViewModel
import kotlinx.coroutines.*

/**
 * @author Baiyu
 * @date :2020/6/13 5:02 PM June
 * @version: 1.0
 */
fun <T> BaseViewModel.launchRequest(
    block: suspend () -> BaseResponse<T>,
    resultState: StatefulMutableLiveData<T>,
    isShowDialog: Boolean = false
) {
    viewModelScope.launch {
        runCatching {
            if (isShowDialog) resultState.value = RequestState.Loading
            withContext(Dispatchers.IO) { block() }
        }.onSuccess {
            if (it.isSuccess())
                resultState.value = RequestState.Success(it.getResponseData())

        }.onFailure {
            resultState.value = RequestState.Error(it.message)
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @param block 请求体方法，必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不传
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.launchRequestVm(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
) {
    //如果需要弹窗 通知Activity/fragment弹窗
    if (isShowDialog) defUI.showDialog.postValue(loadingMessage)
    viewModelScope.launch {
        runCatching {
            //请求代码块调度在Io线程中
            withContext(Dispatchers.IO) { block() }
        }.onSuccess {
            //网络请求成功 关闭弹窗
            defUI.dismissDialog.call()
            try {
                //因为要判断请求的数据结果是否成功，失败会抛出自定义异常，所以在这里try一下
                executeResponse(it) { tIt -> success(tIt) }
            } catch (e: Exception) {
                //打印错误消息
                //失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            //网络请求异常 关闭弹窗
            defUI.dismissDialog.call()
            //打印错误消息

            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 *  不过滤请求结果
 * @param block 请求体 必须要用suspend关键字修饰
 * @param success 成功回调
 * @param error 失败回调 可不给
 * @param isShowDialog 是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
    loadingMessage: String = "请求网络中..."
) {
    //如果需要弹窗 通知Activity/fragment弹窗
    if (isShowDialog) defUI.showDialog.postValue(loadingMessage)
    viewModelScope.launch {
        runCatching {
            //请求时调度在Io线程中
            withContext(Dispatchers.IO) { block() }
        }.onSuccess {
            //网络请求成功 关闭弹窗
            defUI.dismissDialog.call()
            //成功回调
            success(it)
        }.onFailure {
            //网络请求异常 关闭弹窗
            defUI.dismissDialog.call()
            //打印错误消息

            //失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> BaseViewModel.executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        if (response.isSuccess()) success(response.getResponseData())
        else throw AppException(
            response.getResponseCode(),
            response.getResponseMsg(),
            response.getResponseMsg()
        )
    }
}
