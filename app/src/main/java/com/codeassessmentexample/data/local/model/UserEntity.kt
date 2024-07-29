package com.codeassessmentexample.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeassessmentexample.data.local.utils.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class UserEntity(@PrimaryKey
                      val id: Int,
                      val name: String,
                      val email: String,
                      val avatar: String)
