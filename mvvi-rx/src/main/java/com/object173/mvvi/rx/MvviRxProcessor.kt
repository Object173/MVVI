package com.object173.mvvi.rx

import io.reactivex.Observable

interface MvviRxProcessor<ViewAction, ViewState> {

    fun bind(actions: Observable<ViewAction>, states: Observable<ViewState>): Observable<ViewAction>
}

class MvviRxCommonProcessor<ViewAction, ViewState>(
    private val processors: List<MvviRxProcessor<ViewAction, ViewState>>
) : MvviRxProcessor<ViewAction, ViewState> {

    constructor(vararg processors: MvviRxProcessor<ViewAction, ViewState>) : this(processors.toList())

    override fun bind(actions: Observable<ViewAction>, states: Observable<ViewState>): Observable<ViewAction> =
        actions.publish { shared ->
            Observable.merge(processors.map { it.bind(shared, states) })
        }
}