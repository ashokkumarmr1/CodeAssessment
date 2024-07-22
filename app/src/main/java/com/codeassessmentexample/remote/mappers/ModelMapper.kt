package com.codeassessmentexample.remote.mappers

interface ModelMapper<M, E> {
    fun mapFromDTO(model: M): E
}