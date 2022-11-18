package org.sopt.sample.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignUpApi {
    @POST("api/user/signup")
    fun singUp(
        @Body request: SignUpRequest
    ): Call<SignUpResponse>
}

@Serializable
data class SignUpRequest(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val nickname: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class SignUpResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("newUser")
    val user: User
)

@Serializable
data class User(
    val id: Int,
    val name: String,
    val profileImage: String?,
    val bio: String?,
    val email: String,
    val password: String
)