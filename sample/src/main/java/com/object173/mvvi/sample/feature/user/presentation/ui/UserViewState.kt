package com.object173.mvvi.sample.feature.user.presentation.ui

import com.object173.mvvi.sample.feature.user.domain.model.User

sealed class UserViewState {

    object Empty : UserViewState()

    object Loading : UserViewState()

    data class Fail(val error: String) : UserViewState()

    data class Content(val user: User) : UserViewState()
}

sealed class UserViewEvent