package com.object173.mvvi.sample.feature.user.domain

import com.object173.mvvi.sample.feature.user.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(name: String): Flow<User> =
        repository.get(name)
}