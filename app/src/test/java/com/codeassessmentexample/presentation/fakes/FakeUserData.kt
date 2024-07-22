package com.codeassessmentexample.presentation.fakes

import com.codeassessmentexample.domain.model.User

object FakeUserData {

    fun getUser() :List<User>
    {
        val userList1 = User(1,"User1", "user1@gmail.com", "")
        val userList2 = User(2,"User2", "user@gmail.com","")
        val userList3 = User(3,"User3", "user3@gmail.com", "")
        val listofUser = listOf<User>(userList1,userList2,userList3)

        return listofUser
    }
}