package com.object173.mvvi.sample.feature.user.domain

import com.object173.mvvi.sample.feature.user.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun get(name: String): Flow<User>
}