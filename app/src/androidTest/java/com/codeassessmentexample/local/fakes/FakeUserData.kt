package com.codeassessmentexample.local.fakes

import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.data.local.model.UserEntity

object FakeUserData {

    fun getUserList() :List<UserEntity>
    {
        val userList1 = UserEntity(1,"User1", "user1@gmail.com", "")
        val userList2 = UserEntity(2,"User2", "user@gmail.com","")
        val userList3 = UserEntity(3,"User3", "user3@gmail.com", "")
        val listofUser = listOf<UserEntity>(userList1,userList2,userList3)

        return listofUser
    }

    fun getUser() : UserEntity
    {
        val result = UserEntity(1,"User1", "user1@gmail.com", "")

        return result
    }
}