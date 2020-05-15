package com.object173.mvvi.flow

import com.object173.mvvi.flow.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object TestDispatcherProvider : DispatcherProvider {

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    override val IO: CoroutineDispatcher
        get() = Dispatchers.Unconfined

    override val Default: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}