package com.object173.mvvi.rx.model

internal sealed class TestViewAction {

    object GetContent : TestViewAction()

    object GetEvent : TestViewAction()

    object GetAction : TestViewAction()

    object ShowAction : TestViewAction()
}

internal sealed class TestViewState {

    object Initial : TestViewState()

    object Content : TestViewState()

    object Action : TestViewState()
}

internal sealed class TestViewEvent {

    object Event : TestViewEvent()
}