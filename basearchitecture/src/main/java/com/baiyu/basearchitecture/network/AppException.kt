package com.baiyu.basearchitecture.network

/**
 * @author Baiyu
 * @date :2020/6/13 3:24 PM June
 * @version: 1.0
 */
class AppException : Exception {

    var errorMsg: String //错误消息
    var errCode: Int = 0 //错误码
    var errorLog: String? =""//错误日志

    constructor( error: String?,errCode: Int) : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
    }

    constructor(errCode: Int, error: String?, errorLog: String? = "") : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
        this.errorLog = errorLog?:this.errorMsg
    }

    constructor(error: Error,e: Throwable?) {
        errCode = error.getKey()
        errorMsg = error.getValue()
        errorLog = e?.message
    }
}