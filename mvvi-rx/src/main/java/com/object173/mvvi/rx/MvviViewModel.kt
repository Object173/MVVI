package com.object173.mvvi.rx

import androidx.lifecycle.MutableLiveData
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.object173.mvvi.core.MvviReducer
import com.object173.mvvi.core.MvviViewModel
import com.object173.mvvi.core.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers

class MvviViewModelRx<ViewAction, ViewState, ViewEvent>(
    processor: MvviProcessorRx<ViewAction, ViewState>,
    reducer: MvviReducer<ViewAction, ViewState, ViewEvent>,
    initialState: ViewState
) : MvviViewModel<ViewAction, ViewState, ViewEvent>() {

    private val stateSubject = BehaviorRelay.createDefault(initialState)
    private val actionSubject = PublishRelay.create<ViewAction>()

    override val viewState = MutableLiveData<ViewState>(initialState)

    override val viewEvent = SingleLiveEvent<ViewEvent>()

    private val compositeDisposable = CompositeDisposable()

    init {
        actionSubject
            .withLatestFrom(stateSubject) { action, state -> reducer.reduce(action, state) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { (state, event) ->
                stateSubject.accept(state)
                event?.let(viewEvent::setValue)
            }
            .addTo(compositeDisposable)

        stateSubject
            .distinctUntilChanged()
            .subscribe(viewState::setValue)
            .addTo(compositeDisposable)

        processor.bind(actionSubject, stateSubject)
            .subscribeOn(Schedulers.computation())
            .subscribe(actionSubject::accept)
            .addTo(compositeDisposable)
    }

    override fun handleAction(action: ViewAction) {
        actionSubject.accept(action)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}