package com.object173.mvvi.sample.feature.user.presentation

import com.object173.mvvi.core.MvviReducer
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewAction
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewEvent
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewState

class UserReducer : MvviReducer<UserViewAction, UserViewState, UserViewEvent> {

    override fun reduce(action: UserViewAction, state: UserViewState): Pair<UserViewState, UserViewEvent?> =
        when (action) {
            is UserViewAction.Loading  -> UserViewState.Loading to null
            is UserViewAction.ShowUser -> UserViewState.Content(action.user) to null
            is UserViewAction.ShowFail -> UserViewState.Fail(action.error) to null
            else                       -> state to null
        }
}