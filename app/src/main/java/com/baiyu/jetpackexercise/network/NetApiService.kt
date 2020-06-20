package com.baiyu.jetpackexercise.network


import com.baiyu.jetpackexercise.response.ApiResponse
import com.baiyu.jetpackexercise.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetApiService {

    companion object {
        const val SERVER_URL = "https://appapi.hcbbs.com/index.php/"
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("api/User/up_login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<LoginResponse>


}