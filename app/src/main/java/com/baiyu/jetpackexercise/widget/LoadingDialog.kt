package com.baiyu.jetpackexercise.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * @author Baiyu
 * @date :2020/6/14 11:21 AM June
 * @version: 1.0
 */
class LoadingDialog(
    context: Context?,
    layout: View?,
    style: Int
) : Dialog(context!!, style) {
    //    style引用style样式
    init {
        setContentView(layout!!)
        val window = window
        var params: WindowManager.LayoutParams? = null
        if (window != null) {
            params = window.attributes
            val windowManager = window.windowManager
            val display = windowManager.defaultDisplay
            params.gravity = Gravity.CENTER
            params.height = display.height //设置宽度
            params.width = display.width //设置宽度
        }
        if (window != null) {
            window.attributes = params
        }
    }
}