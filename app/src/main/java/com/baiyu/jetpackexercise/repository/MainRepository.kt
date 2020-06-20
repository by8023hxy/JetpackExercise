package com.baiyu.jetpackexercise.repository

import com.baiyu.jetpackexercise.network.NetworkApi
import com.baiyu.jetpackexercise.response.ApiResponse
import com.baiyu.jetpackexercise.response.LoginResponse
import kotlinx.coroutines.delay

/**
 * @author Baiyu
 * @date :2020/6/13 4:26 PM June
 * @version: 1.0
 */
class MainRepository {

    suspend fun login(userName: String, password: String):ApiResponse<LoginResponse> {
        return NetworkApi.service.login(userName,password)
    }
}