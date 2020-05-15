package com.object173.mvvi.sample.feature.user.data.converter

import com.object173.mvvi.sample.feature.user.data.network.dto.UserDto
import com.object173.mvvi.sample.feature.user.domain.model.User

class UserDtoConverter {

    fun convert(from: UserDto): User =
        User(from.id, from.login, from.url)
}