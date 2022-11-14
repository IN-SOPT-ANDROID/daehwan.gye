package org.sopt.sample

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import java.net.ConnectException
import java.util.function.Consumer

open class Response<T>(
    private val success: () -> Unit,
    private vararg val failures: Consumer<Int>
) : Callback<T> {

    override fun onResponse(
        call: Call<T>,
        response: retrofit2.Response<T>
    ) {
        if (response.isSuccessful) {
            success.invoke()
        } else {
            failures.forEach { it.accept(response.code()) }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t.cause is ConnectException) {
            Log.e(
                "NETWORK ERROR", "connection error \n " +
                        "[url] : ${call.request().url} \n" +
                        "[headers] ${call.request().headers.toMultimap()} \n", t
            )
        } else {
            Log.e("ERROR", t.message, t)
        }
    }
}