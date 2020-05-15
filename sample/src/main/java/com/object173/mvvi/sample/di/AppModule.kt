package com.object173.mvvi.sample.di

import org.koin.core.module.Module

object AppModule {

    val modules: List<Module> = networkModule + coroutinesModule + featureModule
}