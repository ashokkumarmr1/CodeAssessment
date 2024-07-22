package com.codeassessmentexample.data.mappers

import com.codeassessmentexample.remote.modelDTO.UserModel

interface Mapper<E, D> {

    fun mapToModel(type: D): E
}