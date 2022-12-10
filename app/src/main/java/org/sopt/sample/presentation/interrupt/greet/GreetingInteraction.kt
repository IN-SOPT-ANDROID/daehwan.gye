package org.sopt.sample.presentation.interrupt.greet

import org.sopt.sample.presentation.interrupt.Interrupt

class SignInInterrupt(
    private val id: String,
    private val pw: String
) : Interrupt<String> {
    override fun sync(): Interrupt.Result<String> {
        TODO("Not yet implemented")
    }

    override fun async() {
        TODO("Not yet implemented")
    }
}