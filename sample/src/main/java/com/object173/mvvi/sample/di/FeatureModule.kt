package com.object173.mvvi.sample.di

import com.object173.mvvi.sample.feature.user.di.UserModule
import org.koin.core.module.Module

val featureModule: List<Module> = UserModule.modules