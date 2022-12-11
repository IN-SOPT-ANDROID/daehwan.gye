package org.sopt.sample.presentation.interrupt.greet

import org.sopt.sample.presentation.interrupt.NetworkInterrupt

class SignInNetworkInterrupt(
    private val id: String,
    private val pw: String
) : NetworkInterrupt {
    override fun sync(): NetworkInterrupt.NetworkResult {
        TODO("Not yet implemented")
    }

    override fun async() {
        TODO("Not yet implemented")
    }
}

class SignUpNetworkInterrupt(
    private val nickname: String,
    private val email: String,
    private val password: String,
    private val passwordValidation: String
) : NetworkInterrupt {
    override fun sync(): NetworkInterrupt.NetworkResult {
        TODO("Not yet implemented")
    }

    override fun async() {
        TODO("Not yet implemented")
    }
}