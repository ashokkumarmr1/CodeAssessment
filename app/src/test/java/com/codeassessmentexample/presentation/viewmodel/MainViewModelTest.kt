package com.codeassessmentexample.presentation.viewmodel

import com.codeassessmentexample.common.Resource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import com.codeassessmentexample.utils.CoroutineScopeRule
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.usecase.UserUseCase
import com.codeassessmentexample.presentation.viewmodel.MainViewModel
import com.codeassessmentexample.presentation.fakes.FakeUserData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest
{

    @get:Rule
    val testCoroutineRule = CoroutineScopeRule()

    val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var  useCase: UserUseCase
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(testDispatcher,useCase)
    }


    @Test
    fun `when result fetching results success then`() = testCoroutineRule.runTest {

        //Arrange (Given)
         val testValue = FakeUserData.getUser()
        `when`(useCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(testValue)) })

        //Act
        useCase.getUsers()

        //Then
        val result = useCase.getUsers().first().data
        //Assert (Then)
        assertEquals(testValue,result)
    }


    @Test
    fun `when result fetching results success data then`() = testCoroutineRule.runTest {

        //Arrange (Given)
        val testValue = FakeUserData.getUser()
        `when`(useCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(testValue)) })

        //Act (when)
        useCase.getUsers()

        //Then
        val result = useCase.getUsers().first().data
        //Assert
        assertEquals(testValue,  result)
    }

    @Test
    fun `when result fetching results failure data then`() = testCoroutineRule.runTest {
        //Arrange (Given)
        val emptyList = listOf<User>()
        `when`(useCase.getUsers()).thenReturn(flow {
            emit(Resource.Error("Throw Exception",emptyList)) })

        //Act (when)
        useCase.getUsers()

        //Then
        val result = useCase.getUsers().first().message
        //Assert
        assertEquals("Throw Exception",  result)
    }

    @Test
    fun `when result fetching results State Success data then`() = testCoroutineRule.runTest() {

        val testValue = FakeUserData.getUser()

            viewModel._uiState.value = viewModel.uiState.value.copy(
                userList =  testValue,
                isLoading = false)

        assertEquals(viewModel.uiState.value.userList, testValue)
    }

    @Test
    fun `when result fetching results State Failure data then`() = testCoroutineRule.runTest() {

        val listofUser = listOf<User>()

        viewModel._uiState.value = viewModel.uiState.value.copy(
            userList =  listofUser,
            isLoading = true,
            error ="")

        assertEquals(viewModel.uiState.value.isLoading, true)
    }

    @Test
    fun `when result fetching results with State success then`() = runBlocking {
        //Arrange (When)
        val testValue = FakeUserData.getUser()
        `when`(useCase.getUsers()).thenReturn(flow {
            emit(Resource.Success(testValue)) })

        //Act (when)
        useCase.getUsers()

        //Then
        val result = useCase.getUsers().first().data
        //Assert
        assertEquals(testValue,result)
    }


    @After
    fun tearDown() {
    }
}