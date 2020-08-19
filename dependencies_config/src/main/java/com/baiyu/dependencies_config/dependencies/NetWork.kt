package com.baiyu.dependencies_config.dependencies

/**
 * @author Baiyu
 * @date :2020/6/13 11:37 AM June
 * @version: 1.0
 */
object NetWork {

    //协程
    object Coroutines {
        private const val coroutinesVersion = "1.3.7"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    }

    object OkHttp{
        private const val okhttpVersion = "4.7.2"
        const val okhttp_log="com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
        const val okhttp="com.squareup.okhttp3:okhttp:$okhttpVersion"
    }
}