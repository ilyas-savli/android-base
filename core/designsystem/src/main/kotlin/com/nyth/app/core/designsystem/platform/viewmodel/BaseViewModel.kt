package com.nyth.app.core.designsystem.platform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyth.app.core.network.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.properties.Delegates

/**
 * Base ViewModel to manage to put all common methods and manage
 * @param ViewState is for storing state of the view
 * @param ViewAction is for storing all actions about the view
 */
abstract class BaseViewModel<ViewState : BaseViewState,
        ViewAction : BaseAction>(initialState: ViewState) :
    ViewModel() {
    private val mutex = Mutex()

    /**
     * For storing ViewState
     */
    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiState = _uiStateFlow.asStateFlow()

    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    /**
     * Delegate will handle state event deduplication
     * (multiple states of the same type holding the same data
     * will not be dispatched multiple times to LiveData stream)
     */
    protected var state by Delegates.observable(initialState) { _, old, new ->
        viewModelScope.launch {
            _uiStateFlow.emit(new)
        }

        if (new != old) {
            stateTimeTravelDebugger?.apply {
                addStateTransition(old, new)
                logLast()
            }
        }
    }

    fun sendAction(viewAction: ViewAction) {
        onReduceState(viewAction)?.let {
            stateTimeTravelDebugger?.addAction(viewAction)
            state = it
        }
    }

    fun updateState(viewState: ViewState) {
        viewModelScope.launch(Dispatchers.IO) {
            mutex.withLock {
                state = viewState
            }
        }
    }

    open fun getString(resId: Int): String? = null

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState?
}
