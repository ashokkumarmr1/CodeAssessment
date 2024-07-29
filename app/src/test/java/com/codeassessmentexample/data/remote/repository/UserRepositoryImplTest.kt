package com.codeassessmentexample.data.remote.repository

import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.data.UserRepositoryImpl
import com.codeassessmentexample.data.local.UserDataSourceImpl
import com.codeassessmentexample.data.remote.UserRemoteImpl
import com.codeassessmentexample.data.remote.fakedata.FakeUserData
import com.codeassessmentexample.utils.CoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest
{
    @Mock
    lateinit var userRepositoryImpl: UserRepositoryImpl
    @Mock
    lateinit var userRemoteImpl: UserRemoteImpl
    @Mock
    lateinit var userDataSourceImpl: UserDataSourceImpl

    @get:Rule
    val testCoroutineRule = CoroutineScopeRule()

    @Before
    fun setUp() {
        userRepositoryImpl = UserRepositoryImpl(userRemoteImpl, userDataSourceImpl)
    }

    @Test
    fun `when result fetching results success then`() = runTest {

        //Arrange (Given)
        val listofUser = FakeUserData.getUserList()

        Mockito.`when`(userRemoteImpl.getUsers()).thenReturn(flow {
            emit(Resource.Success(listofUser)) })

        //when
        userRemoteImpl.getUsers()

        //Then
        val result = userRemoteImpl.getUsers().first().data
        //Assert
        Assert.assertEquals(listofUser,result)
    }

    @Test
    fun `when result fetching error results success then`() = runTest {

        Mockito.`when`(userRemoteImpl.getUsers()).thenReturn(flow {
            emit(Resource.Error("Response Issue")) })

        //when
        userRemoteImpl.getUsers()

        //Then
        val result = userRemoteImpl.getUsers().first().message

        Assert.assertEquals("Response Issue", result)
    }

}