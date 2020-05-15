package com.object173.mvvi.rx

import com.object173.mvvi.core.MvviReducer
import com.object173.mvvi.rx.model.TestViewAction
import com.object173.mvvi.rx.model.TestViewEvent
import com.object173.mvvi.rx.model.TestViewState

internal class TestMvviRouter : MvviReducer<TestViewAction, TestViewState, TestViewEvent> {

    override fun reduce(action: TestViewAction,
                        state: TestViewState): Pair<TestViewState, TestViewEvent?> =
        when (action) {
            TestViewAction.GetContent -> TestViewState.Content to null
            TestViewAction.GetEvent   -> state to TestViewEvent.Event
            TestViewAction.GetAction  -> TestViewState.Action to null
            else                      -> state to null
        }
}