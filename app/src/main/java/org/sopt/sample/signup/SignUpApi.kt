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
    @Header("status")
    val status: Int,
    @SerialName("success")
    val success: Boolean,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: String
)