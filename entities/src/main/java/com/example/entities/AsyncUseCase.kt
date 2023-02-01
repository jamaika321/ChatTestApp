package com.example.entities

import kotlinx.coroutines.*

abstract class AsyncUseCase<out Type, in Params> {
    private val ioContext = Dispatchers.IO
    private val mainContext = Dispatchers.Main

    private var parentJob: Job = Job()

    abstract suspend fun run(params: Params): Type

    operator fun invoke(params: Params, onResult: (Type) -> Unit = {}) {
        unsubscribe()
        parentJob = Job()

        CoroutineScope(mainContext + parentJob).launch {
            val result = withContext(ioContext) {
                run(params)
            }

            onResult(result)
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}