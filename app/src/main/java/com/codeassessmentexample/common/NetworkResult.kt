package com.codeassessmentexample.common

sealed class NetworkResult<out T, out E>
{
    class Success<out T : Any>(val data : T) : NetworkResult<T,Nothing>()
    class Error<out E : Any>(val error : E) : NetworkResult<Nothing, E>()
}
