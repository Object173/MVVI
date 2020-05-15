package com.object173.mvvi.sample.feature.user.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.object173.mvvi.R
import com.object173.mvvi.core.MvviViewModel
import com.object173.mvvi.core.ui.MvviView
import com.object173.mvvi.core.ui.bindViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment(), MvviView<UserViewAction, UserViewState, UserViewEvent> {

    override val viewModel: MvviViewModel<UserViewAction, UserViewState, UserViewEvent> by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.main_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel(viewLifecycleOwner)

        find_user_button.setOnClickListener {
            viewModel.handleAction(UserViewAction.SearchUser(user_name_field.text.toString()))
        }
    }

    override fun render(viewState: UserViewState) {
        when (viewState) {
            is UserViewState.Content -> {
                user_id.text = viewState.user.id.toString()
                user_login.text = viewState.user.login
                user_url.text = viewState.user.url
            }

            is UserViewState.Fail -> {
                error_message.text = viewState.error
            }
        }

        progress.isVisible = viewState is UserViewState.Loading
        content.isVisible = viewState is UserViewState.Content
        error_message.isVisible = viewState is UserViewState.Fail
    }

    override fun handleEvent(viewEvent: UserViewEvent) = Unit
}
