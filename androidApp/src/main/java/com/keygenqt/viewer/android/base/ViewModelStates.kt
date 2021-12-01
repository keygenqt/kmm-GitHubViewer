package com.keygenqt.viewer.android.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class ViewModelStates : ViewModel() {

    /**
     * [MutableStateFlow] for state
     */
    private val _state: MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState.Start)

    /**
     * [StateFlow] for [_state]
     */
    val state: StateFlow<ViewModelState> get() = _state.asStateFlow()

    /**
     * Set state Stop
     */
    fun setStop() {
        _state.value = ViewModelState.Stop
    }

    /**
     * Set state Action
     */
    fun setAction() {
        _state.value = ViewModelState.Action
    }

    /**
     * Set state Error
     */
    fun setError(message: String) {
        _state.value = ViewModelState.Error(message)
    }

    /**
     * Set state Success
     */
    fun <T> setSuccess(data: T) {
        _state.value = ViewModelState.Success(data)
    }
}