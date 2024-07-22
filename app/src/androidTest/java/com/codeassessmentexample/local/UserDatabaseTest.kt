package com.codeassessmentexample.local

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.database.UserDatabase
import com.codeassessmentexample.local.fakes.FakeUserData
import com.codeassessmentexample.local.mappers.UserEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDatabaseTest {

    @Mock
    lateinit var userDAO: UserDAO

    private lateinit var database: UserDatabase

    private lateinit var context: Context


    @Before
    fun setUp() {
        userDAO = database.userDAO()
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .allowMainThreadQueries().build()
    }


    @Test
    fun saveUserList_return_True() =
        runTest {
            // Arrange (Given)
            val tempValue = FakeUserData.getUserList()
            // Saving users to database so we can get it when select query executes
            userDAO.addUserAll(tempValue)

            // Act (When)
            val usersList = userDAO.getCharacters()

            // Assert (Then)
            assertEquals(usersList.count(), 3)
        }

    @Test
    fun saveUser_returnsTrue() =
        runBlocking() {
            // Arrange (Given)
            val tempValue = FakeUserData.getUser()
            // Saving characters to database so we can get it when select query executes
            userDAO.addUser(tempValue)

            // Act (When)
            val result = userDAO.getCharacters()

            // Assert (Then)
            assertEquals(tempValue, result.get(result.count()-1))
        }

    @Test
    fun delete_returnsTrue() = runBlocking {
        val tempValue = FakeUserData.getUserList()

        userDAO.addUser(tempValue.get(0))
        userDAO.addUser(tempValue.get(1))

        userDAO.clearCharacters()

        val latch = CountDownLatch(1)
        val job = async (Dispatchers.IO){
            userDAO.getCharacters().map { assertEquals(it.name,"User1")
            latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
