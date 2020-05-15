package com.object173.mvvi.sample.feature.user.data

import com.object173.mvvi.flow.util.DispatcherProvider
import com.object173.mvvi.sample.feature.user.data.converter.UserDtoConverter
import com.object173.mvvi.sample.feature.user.domain.UserRepository
import com.object173.mvvi.sample.feature.user.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val dataSource: UserDataSource,
    private val converter: UserDtoConverter,
    private val dispatcherProvider: DispatcherProvider
) : UserRepository {

    override suspend fun get(name: String): Flow<User> =
        flow { emit(dataSource.get(name)) }
            .map { dto -> converter.convert(dto) }
            .flowOn(dispatcherProvider.IO)
}