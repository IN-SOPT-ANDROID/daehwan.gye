package org.sopt.sample.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @POST("api/user/signin")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("test")
    fun test(): Call<LoginResponse>
}

@Serializable
data class LoginRequest(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class LoginResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("result")
    val result: Result
)

@Serializable
data class Result(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String?,
    @SerialName("bio")
    val bio: String?,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)