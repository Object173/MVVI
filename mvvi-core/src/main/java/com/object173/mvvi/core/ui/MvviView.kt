package com.object173.mvvi.core.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.object173.mvvi.core.MvviViewModel

interface MvviView<ViewAction, ViewState, ViewEvent> {

    val viewModel: MvviViewModel<ViewAction, ViewState, ViewEvent>

    fun render(viewState: ViewState)

    fun handleEvent(viewEvent: ViewEvent)
}

fun <ViewAction, ViewState, ViewEvent> MvviView<ViewAction, ViewState, ViewEvent>.bindViewModel(
    owner: LifecycleOwner,
    initialAction: ViewAction? = null
) {
    viewModel.viewState.observe(owner, Observer(::render))

    viewModel.viewEvent.observe(owner, Observer { event ->
        event?.let(::handleEvent)
    })

    initialAction?.let(viewModel::handleAction)
}