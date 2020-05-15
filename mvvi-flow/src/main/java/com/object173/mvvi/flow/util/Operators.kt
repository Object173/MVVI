package com.object173.mvvi.flow.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

private object UNINITIALIZED

fun <A, B, R> Flow<A>.withLatestFrom(other: Flow<B>, transform: suspend (A, B) -> R): Flow<R> =
    flow {
        coroutineScope {
            val latestB = AtomicReference<Any>(UNINITIALIZED)
            val outerScope = this

            launch {
                try {
                    other.collect { latestB.set(it) }
                } catch (e: CancellationException) {
                    outerScope.cancel(e)
                }
            }

            collect { a: A ->
                latestB.get().let {
                    if (it != UNINITIALIZED) {
                        emit(transform(a, it as B))
                    }
                }
            }
        }
    }

fun <T, R> Flow<T>.flatMapFirst(transform: suspend (value: T) -> Flow<R>): Flow<R> =
    map(transform).flattenFirst()

fun <T> Flow<Flow<T>>.flattenFirst(): Flow<T> = channelFlow {
    val outerScope = this
    val busy = AtomicBoolean(false)
    collect { inner ->
        if (busy.compareAndSet(false, true)) {
            launch {
                try {
                    inner.collect { outerScope.send(it) }
                    busy.set(false)
                } catch (e: CancellationException) {
                    outerScope.cancel(e)
                }
            }
        }
    }
}