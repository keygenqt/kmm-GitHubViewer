package com.keygenqt.viewer.android.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keygenqt.response.ResponseResult
import com.keygenqt.response.extensions.error
import com.keygenqt.response.extensions.errorUnknownHost
import com.keygenqt.response.extensions.success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

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
     * Set state exception Error
     */
    fun setError(exception: Exception) {
        _state.value = ViewModelState.Error(exception.message ?: "Exception error")
    }

    /**
     * Set state Success
     */
    fun <T> setSuccess(data: T) {
        _state.value = ViewModelState.Success(data)
    }

    /**
     * Launch query only loading
     */
    fun <T> queryLaunch(
        predicate: suspend () -> ResponseResult<T>
    ) {
        setAction()
        viewModelScope.launch {
            predicate()
                .success(::setSuccess)
                .error(::setError)
                .error { Timber.e(it) }
                .errorUnknownHost(::setError)
        }
    }
}