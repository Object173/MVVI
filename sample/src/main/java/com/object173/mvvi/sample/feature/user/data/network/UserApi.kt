package com.object173.mvvi.sample.feature.user.data.network

import com.object173.mvvi.sample.feature.user.data.network.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): UserDto
}