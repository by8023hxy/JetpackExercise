package com.baiyu.jetpackexercise.base

import androidx.lifecycle.ViewModel
import com.baiyu.basearchitecture.event.SingleLiveEvent

/**
 * @author Baiyu
 * @date :2020/6/13 4:17 PM June
 * @version: 1.0
 */
open class BaseViewModel :ViewModel(){

    val defUI: UIChange by lazy { UIChange() }

    /**
     * UI事件
     */
    inner class UIChange {
        //显示加载框
        val showDialog by lazy { SingleLiveEvent<String>() }
        //隐藏
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
    }
}