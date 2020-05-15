package com.object173.mvvi.rx

sealed class TestViewAction {

    object Action1 : TestViewAction()

    object Action2 : TestViewAction()

    object Action3 : TestViewAction()
}

sealed class TestViewState {

    object Initial : TestViewState()

    object Content : TestViewState()
}

sealed class TestViewEvent {

    object Event : TestViewEvent()
}