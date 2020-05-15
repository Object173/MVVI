package com.object173.mvvi.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flattenMerge

interface MvviFlowProcessor<ViewAction, ViewState> {

    fun bind(actions: Flow<ViewAction>, states: Flow<ViewState>): Flow<ViewAction>
}

class MvviFlowProcessorCommon<ViewAction, ViewState>(
    private val processors: List<MvviFlowProcessor<ViewAction, ViewState>>
) : MvviFlowProcessor<ViewAction, ViewState> {

    constructor(vararg processors: MvviFlowProcessor<ViewAction, ViewState>) : this(processors.toList())

    override fun bind(actions: Flow<ViewAction>, states: Flow<ViewState>): Flow<ViewAction> =
        processors.map { it.bind(actions, states) }
            .asFlow()
            .flattenMerge()
}