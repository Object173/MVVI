package com.object173.mvvi.core

interface MvviReducer<ViewAction, ViewState, ViewEvent> {

    fun reduce(action: ViewAction, state: ViewState): Pair<ViewState, ViewEvent?>
}