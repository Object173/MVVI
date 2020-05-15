package com.object173.mvvi.rx

import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom

class TestMvviProcessor : MvviProcessorRx<TestViewAction, TestViewState> {

    override fun bind(actions: Observable<TestViewAction>,
                      states: Observable<TestViewState>): Observable<TestViewAction> =
        actions.ofType(TestViewAction.Action2::class.java)
            .withLatestFrom(states) { action, state -> action to state }
            .map { (action, state) ->
                if (state is TestViewState.Initial) {
                    TestViewAction.Action3
                } else {
                    TestViewAction.Action1
                }
            }
}