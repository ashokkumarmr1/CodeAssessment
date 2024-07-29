package com.codeassessmentexample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeassessmentexample.data.local.model.UserEntity

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUserAll(userList : List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getCharacters(): List<UserEntity>

    @Query("SELECT * FROM user WHERE  id = :id")
    fun getCharacter(id: Long): UserEntity

    @Query("DELETE FROM user")
    fun clearCharacters(): Int
}