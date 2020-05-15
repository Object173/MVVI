package com.object173.mvvi.flow.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

    val Main: CoroutineDispatcher

    val IO: CoroutineDispatcher

    val Default: CoroutineDispatcher
}

object DefaultDispatcherProvider : DispatcherProvider {

    override val Main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default
}