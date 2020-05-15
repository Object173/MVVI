package com.object173.mvvi.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.object173.mvvi.core.util.SingleLiveEvent

abstract class MvviViewModel<ViewAction, ViewState, ViewEvent> : ViewModel() {

    abstract val viewState: LiveData<ViewState>
    abstract val viewEvent: SingleLiveEvent<ViewEvent>

    abstract fun handleAction(action: ViewAction)
}