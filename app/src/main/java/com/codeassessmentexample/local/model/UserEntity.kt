package com.codeassessmentexample.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codeassessmentexample.local.utils.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class UserEntity(@PrimaryKey
                      val id: Int,
                      val name: String,
                      val email: String,
                      val avatar: String)
