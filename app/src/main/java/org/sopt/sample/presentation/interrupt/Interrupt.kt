package org.sopt.sample.presentation.interrupt

interface Interrupt<RESULT> {
    fun sync(): Result<RESULT>

    fun async(): Unit

    data class Result<RESULT>(
        val status: Int,
        val detail: RESULT
    ) {
        fun isSucceed() = status == 200
    }
}