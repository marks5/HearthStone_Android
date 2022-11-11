package me.gabriel.hearthstone.extension

import kotlinx.coroutines.flow.Flow

/**
 * Collects and return an object of type [T], provided by the [collect] operator.
 *
 * Throws an exception if the [collect] block is not called for any reason.
 *
 */
suspend fun <T> Flow<T>.collectResult(): T {
    var result: T? = null
    collect {
        result = it
    }
    return result ?: throw Exception()
}

/**
 * Replaces the [collect] operator.
 * Indicates that a remote or local service has returned successfully
 * and performs the provided [action].
 *
 * Just a name change.
 *
 */
suspend fun <T> Flow<T>.onSuccess(action: suspend (value: T) -> Unit): Unit =
    collect { action(it) }
