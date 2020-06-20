package com.baiyu.basearchitecture.extensions

import androidx.lifecycle.viewModelScope
import com.baiyu.basearchitecture.base.BaseCoroutinesViewModel
import com.baiyu.basearchitecture.event.RequestState
import com.baiyu.basearchitecture.livedata.StatefulMutableLiveData
import com.baiyu.basearchitecture.network.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Baiyu
 * @date :2020/6/13 3:25 PM June
 * @version: 1.0
 */

fun <T> BaseCoroutinesViewModel.launchRequest(
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

