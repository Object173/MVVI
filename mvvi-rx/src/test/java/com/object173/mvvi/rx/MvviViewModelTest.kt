package com.object173.mvvi.rx

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.object173.mvvi.core.MvviReducer
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MvviViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testSchedulerRule = TestSchedulerRule()

    private val processor = MvviRxProcessorCommon(TestMvviProcessor())
    private val reducer: MvviReducer<TestViewAction, TestViewState, TestViewEvent> = mock()

    private val viewModel by lazy {
        MvviViewModelRx(processor, reducer, TestViewState.Initial)
    }

    @Test
    fun `handle action with new state EXPECT change view state`() {
        whenever(reducer.reduce(TestViewAction.Action1, TestViewState.Initial))
            .thenReturn(TestViewState.Content to null)

        viewModel.handleAction(TestViewAction.Action1)

        assertEquals(TestViewState.Content, viewModel.viewState.value)
    }

    @Test
    fun `handle action with event EXPECT send view event`() {
        whenever(reducer.reduce(TestViewAction.Action1, TestViewState.Initial))
            .thenReturn(TestViewState.Initial to TestViewEvent.Event)

        viewModel.handleAction(TestViewAction.Action1)

        assertEquals(TestViewEvent.Event, viewModel.viewEvent.value)
    }

    @Test
    fun `handle action with new action EXPECT handle both actions`() {
        whenever(reducer.reduce(TestViewAction.Action2, TestViewState.Initial))
            .thenReturn(TestViewState.Initial to TestViewEvent.Event)
        whenever(reducer.reduce(TestViewAction.Action3, TestViewState.Initial))
            .thenReturn(TestViewState.Content to null)

        viewModel.handleAction(TestViewAction.Action2)

        assertEquals(TestViewState.Content, viewModel.viewState.value)
        assertEquals(TestViewEvent.Event, viewModel.viewEvent.value)
    }
}