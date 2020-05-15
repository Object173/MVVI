package com.object173.mvvi.sample.feature.user.di

import com.object173.mvvi.core.MvviViewModel
import com.object173.mvvi.flow.MvviFlowProcessorCommon
import com.object173.mvvi.flow.MvviViewModelFlow
import com.object173.mvvi.flow.util.DispatcherProvider
import com.object173.mvvi.sample.feature.user.data.UserDataSource
import com.object173.mvvi.sample.feature.user.data.UserDataSourceImpl
import com.object173.mvvi.sample.feature.user.data.UserRepositoryImpl
import com.object173.mvvi.sample.feature.user.data.converter.UserDtoConverter
import com.object173.mvvi.sample.feature.user.data.network.UserApi
import com.object173.mvvi.sample.feature.user.domain.GetUserUseCase
import com.object173.mvvi.sample.feature.user.domain.UserRepository
import com.object173.mvvi.sample.feature.user.presentation.UserReducer
import com.object173.mvvi.sample.feature.user.presentation.processor.SearchUserProcessor
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewAction
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewEvent
import com.object173.mvvi.sample.feature.user.presentation.ui.UserViewState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

object UserModule {

    private val userDataModule = module {
        single { get<Retrofit>().create(UserApi::class.java) } bind UserApi::class
        single { UserDataSourceImpl(get()) } bind UserDataSource::class
        single { UserDtoConverter() } bind UserDtoConverter::class
        single { UserRepositoryImpl(get(), get(), get()) } bind UserRepository::class
    }

    private val userDomainModule = module {
        single { GetUserUseCase(get()) } bind GetUserUseCase::class
    }

    private val userPresentationModule = module {
        viewModel {
            MvviViewModelFlow(
                MvviFlowProcessorCommon(SearchUserProcessor(get())),
                UserReducer(),
                UserViewState.Empty,
                get<DispatcherProvider>()
            ) as MvviViewModel<UserViewAction, UserViewState, UserViewEvent>
        }
    }

    val modules: List<Module> = listOf(userDataModule, userDomainModule, userPresentationModule)
}