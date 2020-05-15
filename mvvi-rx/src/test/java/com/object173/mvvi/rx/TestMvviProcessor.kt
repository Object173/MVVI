package com.object173.mvvi.rx

import com.object173.mvvi.rx.model.TestViewAction
import com.object173.mvvi.rx.model.TestViewState
import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom

internal class TestMvviProcessor : MvviRxProcessor<TestViewAction, TestViewState> {

    override fun bind(actions: Observable<TestViewAction>,
                      states: Observable<TestViewState>): Observable<TestViewAction> =
        actions.ofType(TestViewAction.GetAction::class.java)
            .withLatestFrom(states) { action, state -> action to state }
            .map { (action, state) -> TestViewAction.ShowAction }
}