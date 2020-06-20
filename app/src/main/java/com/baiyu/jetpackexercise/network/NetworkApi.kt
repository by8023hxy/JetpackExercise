package com.baiyu.jetpackexercise.network

import com.baiyu.basearchitecture.network.BaseNetworkApi
import okhttp3.OkHttpClient

object NetworkApi: BaseNetworkApi(){

    //封装NetApiService变量 方便直接快速调用
    val service:NetApiService by lazy {
        getApi(NetApiService::class.java,NetApiService.SERVER_URL)
    }

    /**
     * 实现重写父类的setHttpClientBuilder方法，
     * 在这里可以添加拦截器，可以对Builder做任意操作
     */
    override fun setHttpClientBuilder(builder: OkHttpClient.Builder):OkHttpClient.Builder{
        builder.addInterceptor(LogInterceptor())
        return builder
    }

}



