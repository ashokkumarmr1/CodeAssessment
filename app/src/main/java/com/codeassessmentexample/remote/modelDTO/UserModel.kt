package com.codeassessmentexample.remote.modelDTO

import com.squareup.moshi.Json

data class UserModel(@Json(name = "id")
               val id: Int = 0,
                     @Json(name = "name")
               val name: String = "",
                     @Json(name = "email")
               val email: String = "",
                     @Json(name = "avatar")
               val avatar: String = "")