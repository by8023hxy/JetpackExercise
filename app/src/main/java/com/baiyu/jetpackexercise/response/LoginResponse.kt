package com.baiyu.jetpackexercise.response

/**
 * @author Baiyu
 * @date :2020/6/13 4:34 PM June
 * @version: 1.0
 */
data class LoginResponse(
    val avatar: String,
    val is_aljzp_resume: Int,
    val is_readme: Int,
    val uid: Int,
    val username: String
)
