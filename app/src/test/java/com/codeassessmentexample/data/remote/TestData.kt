package com.codeassessmentexample.data.remote

import com.codeassessmentexample.remote.modelDTO.UserModel
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun testUserList(): List<UserModel> {
    // creating empty arraylist using constructor
    var arraylist = ArrayList<UserModel>()
    //adding String elements in the list
    arraylist.add(UserModel(1,"Android","android@gmail.com",""))
    arraylist.add(UserModel(2,"BlackBerry","blackberry@gmail.com",""))
    arraylist.add(UserModel(2,"Cello","cello@gmail.com",""))

    return arraylist

}

fun testUserEmptyList(): ArrayList<UserModel> {
    // creating empty arraylist using constructor
    var arraylist = ArrayList<UserModel>()
    return arraylist

}

fun getDummyCharacters() = listOf(
    UserModel(
        id      = 1,
        name    = "1",
        email   = "android@gmail.com",
        avatar  = ""
    )
)

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}