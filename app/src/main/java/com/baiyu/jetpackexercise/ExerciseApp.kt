package com.baiyu.jetpackexercise

import android.app.Application
import android.content.Context
import com.tencent.mmkv.MMKV
import kotlin.properties.Delegates

/**
 * @author Baiyu
 * @date :2020/6/13 4:11 PM June
 * @version: 1.0
 */
class ExerciseApp : Application() {
    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        MMKV.initialize(this.filesDir.absolutePath + "/mmkv")

    }
}