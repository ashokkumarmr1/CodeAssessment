package com.codeassessmentexample.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*

import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultDispatchProviderTest  : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val io: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val default: CoroutineDispatcher
        get() = TODO("Not yet implemented")
}