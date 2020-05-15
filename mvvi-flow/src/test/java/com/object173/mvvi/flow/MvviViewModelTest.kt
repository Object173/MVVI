package com.object173.mvvi.flow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.object173.mvvi.flow.model.TestViewAction
import com.object173.mvvi.flow.model.TestViewEvent
import com.object173.mvvi.flow.model.TestViewState
import com.object173.mvvi.flow.util.TestCoroutinesRule
import com.object173.mvvi.flow.util.TestDispatcherProvider
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class MvviViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutinesRule = TestCoroutinesRule()

    private val viewModel by lazy {
        MvviFlowViewModel(MvviFlowProcessorCommon(TestMvviProcessor()),
                          TestMvviRouter(),
                          TestViewState.Initial,
                          TestDispatcherProvider)
    }

    @Test
    fun `handle GetContent action EXPECT view state is Content`() = runBlockingTest {
        viewModel.handleAction(TestViewAction.GetContent)

        assertEquals(TestViewState.Content, viewModel.viewState.value)
    }

    @Test
    fun `handle GetEvent action EXPECT send view event`() = runBlockingTest {
        viewModel.handleAction(TestViewAction.GetEvent)

        assertEquals(TestViewEvent.Event, viewModel.viewEvent.value)
    }

    @Test
    fun `handle GetAction action EXPECT view state is Action`() = runBlockingTest {
        viewModel.handleAction(TestViewAction.GetAction)

        assertEquals(TestViewState.Action, viewModel.viewState.value)
    }
}