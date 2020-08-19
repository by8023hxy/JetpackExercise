package com.baiyu.jetpackexercise.viewmodel

import androidx.lifecycle.MutableLiveData
import com.baiyu.basearchitecture.databind.StringObservableField
import com.baiyu.basearchitecture.livedata.StatefulMutableLiveData
import com.baiyu.jetpackexercise.base.BaseViewModel
import com.baiyu.jetpackexercise.ext.launchRequestVm
import com.baiyu.jetpackexercise.repository.MainRepository
import com.baiyu.jetpackexercise.response.LoginResponse
import com.blankj.utilcode.util.LogUtils

/**
 * @author Baiyu
 * @date :2020/6/13 4:26 PM June
 * @version: 1.0
 */

class MainViewModel : BaseViewModel() {
    val imgUrl = StringObservableField()
    val name = StringObservableField()
    val content = StringObservableField()

    private val repository by lazy { MainRepository() }
    var responseLoginResponse: StatefulMutableLiveData<LoginResponse> = MutableLiveData()


    fun getTest() {
        launchRequestVm({ repository.login("bytest", "123456") }, {
            //请求成功 已自动处理了 请求结果是否正常
            imgUrl.set(it.avatar)
            name.set(it.username)
            content.set(it.uid.toString())
            LogUtils.eTag("22929292922", imgUrl.get())
        }, {
            //请求失败 网络异常，或者请求结果码错误都会回调在这里
            LogUtils.eTag("22929292922", it.errorMsg)
        }, true, "")

    }


}