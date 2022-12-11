package org.sopt.sample.presentation.interrupt

interface Interrupt<RESULT> {
    fun sync(): Result<RESULT>

    fun async(): Unit

    interface Result<RESULT> {
        fun get(): RESULT
        fun isSucceed(): Boolean
    }
}