package com.object173.mvvi.sample.feature.user.presentation.processor

import com.object173.mvvi.flow.MvviFlowProcessor
import com.object173.mvvi.sample.feature.user.domain.GetUserUseCase
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewAction
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewState
import kotlinx.coroutines.flow.*

class SearchUserProcessor(
    private val getUserUseCase: GetUserUseCase
) : MvviFlowProcessor<UserViewAction, UserViewState> {

    override fun bind(actions: Flow<UserViewAction>, state: Flow<UserViewState>): Flow<UserViewAction> =
        actions.filterIsInstance<UserViewAction.SearchUser>()
            .flatMapLatest { action ->
                getUserUseCase(action.name)
                    .map { user -> UserViewAction.ShowUser(user) as UserViewAction }
                    .catch { throwable -> emit(UserViewAction.ShowFail(throwable.message.orEmpty())) }
                    .onStart { emit(UserViewAction.Loading) }
            }
}