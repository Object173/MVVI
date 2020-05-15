package com.object173.mvvi.rx

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.object173.mvvi.rx.model.TestViewAction
import com.object173.mvvi.rx.model.TestViewEvent
import com.object173.mvvi.rx.model.TestViewState
import com.object173.mvvi.rx.util.TestSchedulerRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class MvviViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testSchedulerRule = TestSchedulerRule()

    private val viewModel by lazy {
        MvviViewModelRx(MvviRxProcessorCommon(TestMvviProcessor()),
                        TestMvviRouter(),
                        TestViewState.Initial)
    }

    @Test
    fun `handle GetContent action EXPECT view state is Content`() {
        viewModel.handleAction(TestViewAction.GetContent)

        assertEquals(TestViewState.Content, viewModel.viewState.value)
    }

    @Test
    fun `handle GetEvent action EXPECT send view event`() {
        viewModel.handleAction(TestViewAction.GetEvent)

        assertEquals(TestViewEvent.Event, viewModel.viewEvent.value)
    }

    @Test
    fun `handle GetAction action EXPECT view state is Action`() {
        viewModel.handleAction(TestViewAction.GetAction)

        assertEquals(TestViewState.Action, viewModel.viewState.value)
    }
}