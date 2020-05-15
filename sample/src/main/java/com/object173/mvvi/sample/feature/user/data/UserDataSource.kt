package com.object173.mvvi.sample.feature.user.data

import com.object173.mvvi.sample.feature.user.data.network.UserApi
import com.object173.mvvi.sample.feature.user.data.network.dto.UserDto

interface UserDataSource {

    suspend fun get(name: String): UserDto
}

class UserDataSourceImpl(private val api: UserApi) : UserDataSource {

    override suspend fun get(name: String): UserDto =
        api.getUser(name)
}