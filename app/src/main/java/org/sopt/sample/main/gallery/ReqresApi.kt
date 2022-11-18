package org.sopt.sample.main.gallery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresApi {
    @GET("api/users")
    fun getProfiles(
        @Query(value = "page") page: Int
    ): Call<ReqresResponse>
}

@Serializable
data class ReqresResponse(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPage: Int,
    val data: List<ProfileData>,
    @SerialName("support")
    val support: SupportInfo
)

@Serializable
data class ProfileData(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val avatar: String
)

@Serializable
data class SupportInfo(
    val url: String,
    val text: String
)