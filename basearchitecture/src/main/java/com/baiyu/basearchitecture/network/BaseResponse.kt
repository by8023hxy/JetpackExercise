package com.baiyu.basearchitecture.network

/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
abstract class BaseResponse<T> {

    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSuccess(): Boolean

    abstract fun getResponseData(): T

    abstract fun getResponseCode(): Int

    abstract fun getResponseMsg(): String

}