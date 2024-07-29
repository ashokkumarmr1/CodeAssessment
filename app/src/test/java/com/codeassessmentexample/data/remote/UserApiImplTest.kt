package com.codeassessmentexample.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codeassessmentexample.data.remote.api.UserApiService
import com.codeassessmentexample.utils.CoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserApiImplTest
{
    @Mock
    lateinit var apiService: UserApiService

    lateinit var apiImpl: UserApiImpl

    @get:Rule
    val testCoroutineRule = CoroutineScopeRule()

    private lateinit var server : MockWebServer // Fake Server

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        apiImpl = UserApiImpl(apiService)

        server = MockWebServer()

        server.start(8000)
        var BASE_URL = server.url("https://5e510330f2c0d300147c034c.mockapi.io/").toString()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(UserApiService::class.java)
    }

    @Test
    fun `test UserAPI service Success`() = runTest {

        Mockito.`when`(apiService.getUsers()).thenReturn(Response.success(testUserList()))
        val testMethod = apiImpl.getUsers()
        Assert.assertEquals(testMethod.body()?.size,3)
    }

    @Test
    fun `test UserAPI service error`() = runTest {

        Mockito.`when`(apiService.getUsers()).thenReturn(Response.error(
            400,
            "{\"error\":\"Unauthorized\"}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        ))
        val testMethod = apiImpl.getUsers()
        Assert.assertEquals(testMethod.code(),400)
    }

    @Test
    fun `test UserAPI service PayLoad error`() = runTest {

        Mockito.`when`(apiService.getUsers()).thenReturn(Response.error(
            11000,
            "{\"error\":\"Request payload is invalid\"}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        ))
        val testMethod = apiImpl.getUsers()
        Assert.assertEquals(testMethod.code(),11000)
    }


    @After
    fun tearDown() {
    }
}


