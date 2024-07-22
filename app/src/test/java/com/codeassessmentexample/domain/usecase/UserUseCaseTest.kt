package com.codeassessmentexample.domain.usecase

import com.codeassessmentexample.utils.CoroutineScopeRule
import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserUseCaseTest
{
    private lateinit var getUseCase: UserUseCase

    @get:Rule
    val testCoroutineRule = CoroutineScopeRule()

    @Mock
    private lateinit var mockUserRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUseCase          =   UserUseCase(mockUserRepository)
    }

    @Test
    fun `when result fetching results success then`() = runTest {

        val userList1 = User(1,"User1", "user1@gmail.com", "")
        val userList2 = User(2,"User2", "user@gmail.com","")
        val userList3 = User(3,"User3", "user3@gmail.com", "")
        val listofUser = listOf<User>(userList1,userList2,userList3)

        Mockito.`when`(getUseCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(listofUser)) })

        //when
        getUseCase.getUsers()

        //Then
        val result = getUseCase.getUsers().first().data

        assertEquals(listofUser,result)
    }

    @Test
    fun `when result fetching results success using FakeRespository then`() = runTest {

        val userList1 = User(1,"User1", "user1@gmail.com", "")
        val userList2 = User(2,"User2", "user@gmail.com","")
        val userList3 = User(3,"User3", "user3@gmail.com", "")
        val listofUser = listOf<User>(userList1,userList2,userList3)

        Mockito.`when`(getUseCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(listofUser)) })

        //when
        getUseCase.getUsers()

        //Then
        val result = getUseCase.getUsers().first().data

        assertEquals(listofUser,result)
    }


    @Test
    fun `when result fetching empty results success then`() = runTest {

        val emptyList = listOf<User>()

        Mockito.`when`(getUseCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(emptyList)) })

        //when
        getUseCase.getUsers()

        //Then
        val result = getUseCase.getUsers().first().data

        assertEquals(emptyList,result)
    }

    @Test
    fun `when result fetching error results success then`() = runBlocking {

        Mockito.`when`(getUseCase.getUsers()).thenReturn(flow {
            emit(Resource.Error("Response Issue")) })

        //when
        getUseCase.getUsers()

        //Then
        val result = getUseCase.getUsers().first().message

        assertEquals("Response Issue",result)
    }

    @After
    fun tearDown() {
    }
}

