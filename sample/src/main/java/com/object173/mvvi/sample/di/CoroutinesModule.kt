package com.object173.mvvi.sample.di

import com.object173.mvvi.flow.util.DispatcherProvider
import com.object173.mvvi.flow.util.DefaultDispatcherProvider
import org.koin.dsl.bind
import org.koin.dsl.module

val coroutinesModule = module {

    single { DefaultDispatcherProvider } bind DispatcherProvider::class
}