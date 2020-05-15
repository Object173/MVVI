package com.object173.mvvi.rx

import io.reactivex.Observable

interface MvviProcessorRx<ViewAction, ViewState> {

    fun bind(actions: Observable<ViewAction>, states: Observable<ViewState>): Observable<ViewAction>
}

class MvviRxProcessorCommon<ViewAction, ViewState>(
    private val processors: List<MvviProcessorRx<ViewAction, ViewState>>
) : MvviProcessorRx<ViewAction, ViewState> {

    constructor(vararg processors: MvviProcessorRx<ViewAction, ViewState>) : this(processors.toList())

    override fun bind(actions: Observable<ViewAction>, states: Observable<ViewState>): Observable<ViewAction> =
        actions.publish { shared ->
            Observable.merge(processors.map { it.bind(shared, states) })
        }
}