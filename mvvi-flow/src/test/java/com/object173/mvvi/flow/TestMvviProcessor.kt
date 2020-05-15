package com.object173.mvvi.flow

import com.object173.mvvi.flow.model.TestViewAction
import com.object173.mvvi.flow.model.TestViewState
import com.object173.mvvi.flow.util.withLatestFrom
import kotlinx.coroutines.flow.*

internal  class TestMvviProcessor : MvviFlowProcessor<TestViewAction, TestViewState> {

    override fun bind(actions: Flow<TestViewAction>,
                      states: Flow<TestViewState>): Flow<TestViewAction> =
        actions.filterIsInstance<TestViewAction.GetAction>()
            .withLatestFrom(states) { action, state -> action to state }
            .map { (action, state) -> TestViewAction.ShowAction }
}