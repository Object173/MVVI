package com.object173.mvvi.flow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.object173.mvvi.core.MvviReducer
import com.object173.mvvi.core.MvviViewModel
import com.object173.mvvi.core.util.SingleLiveEvent
import com.object173.mvvi.flow.util.DispatcherProvider
import com.object173.mvvi.flow.util.withLatestFrom
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

open class MvviFlowViewModel<ViewAction, ViewState, ViewEvent>(
    processor: MvviFlowProcessor<ViewAction, ViewState>,
    reducer: MvviReducer<ViewAction, ViewState, ViewEvent>,
    initialState: ViewState,
    dispatcherProvider: DispatcherProvider
) : MvviViewModel<ViewAction, ViewState, ViewEvent>() {

    private val stateChannel = BroadcastChannel<ViewState>(Channel.CONFLATED)
    private val actionChannel = BroadcastChannel<ViewAction>(Channel.BUFFERED)

    override val viewState = MutableLiveData<ViewState>(initialState)

    override val viewEvent = SingleLiveEvent<ViewEvent>()

    init {
        val stateFlow = stateChannel.asFlow()
        val actionFlow = actionChannel.asFlow()

        actionFlow
            .withLatestFrom(stateFlow) { action, state -> reducer.reduce(action, state) }
            .flowOn(dispatcherProvider.Default)
            .onEach { (state, event) ->
                stateChannel.offer(state)
                event?.let(viewEvent::setValue)
            }
            .flowOn(dispatcherProvider.Main)
            .launchIn(viewModelScope)

        stateFlow
            .distinctUntilChanged()
            .onEach { state -> viewState.value = state }
            .launchIn(viewModelScope)

        processor.bind(actionFlow, stateFlow)
            .onEach(actionChannel::send)
            .flowOn(dispatcherProvider.Default)
            .launchIn(viewModelScope)

        stateChannel.offer(initialState)
    }

    override fun handleAction(action: ViewAction) {
        actionChannel.offer(action)
    }
}