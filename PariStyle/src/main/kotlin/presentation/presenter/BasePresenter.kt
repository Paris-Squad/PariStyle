package org.example.presentation.presenter

import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.domain.model.exception.PariStyleException

abstract class BasePresenter<T, S>(initialState: S) {
    protected val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state.asStateFlow()

    protected suspend fun executeWithErrorHandling(
        block: suspend () -> T,
        onError: (Throwable) -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        try {
            val result = block()
            onSuccess(result)
        } catch (e: PariStyleException) {
            onError(e)
        } catch (e: ResponseException) {
            onError(e)
        } catch (e: Exception) {
            onError(e)
        }
    }
}