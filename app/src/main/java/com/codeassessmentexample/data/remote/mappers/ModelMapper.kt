package com.codeassessmentexample.data.remote.mappers

interface ModelMapper<M, E> {
    fun mapToModel(model: M): E
}