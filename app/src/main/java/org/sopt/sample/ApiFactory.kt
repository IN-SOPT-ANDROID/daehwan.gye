package org.sopt.sample

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.sopt.sample.login.LoginApi
import org.sopt.sample.main.gallery.ReqresApi
import org.sopt.sample.signup.SignUpApi
import retrofit2.Retrofit

object ApiFactory {
    val retrofit by lazy {
        ApiUrlRetrofit.Reqres.retrofit()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ApiPool {
    val loginApi = ApiFactory.create<LoginApi>()
    val signUpApi = ApiFactory.create<SignUpApi>()
    val reqresApi = ApiFactory.create<ReqresApi>()
}

private enum class ApiUrlRetrofit(
    val retrofit: Retrofit
) {
    LOCALHOST(
        Retrofit.Builder()
            .baseUrl(ApiUrl.LOCALHOST.asHttp())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    ) {
        override fun retrofit(): Retrofit = retrofit
    },

    SOPT(
        Retrofit.Builder()
            .baseUrl(ApiUrl.SOPT.asHttp())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    ) {
        override fun retrofit(): Retrofit = retrofit
    },

    Reqres(
        Retrofit.Builder()
            .baseUrl(ApiUrl.REQRES.asHttps())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    ) {
        override fun retrofit() = retrofit
    }

    ;

    abstract fun retrofit(): Retrofit
}