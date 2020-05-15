package com.object173.mvvi.sample.feature.user.presentation.ui

import com.object173.mvvi.sample.feature.user.domain.model.User

sealed class UserViewAction {

    object Loading : UserViewAction()

    data class SearchUser(val name: String) : UserViewAction()

    data class ShowUser(val user: User) : UserViewAction()

    data class ShowFail(val error: String) : UserViewAction()
}