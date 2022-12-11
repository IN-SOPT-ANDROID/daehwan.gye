package org.sopt.sample.presentation.interrupt

interface NetworkInterrupt : Interrupt<NetworkInterrupt.NetworkResult> {
    override fun sync(): NetworkResult
    override fun async()

    data class NetworkResult(
        val status: Int,
        val detail: String
    ) : Interrupt.Result<NetworkResult> {
        override fun get() = this
        override fun isSucceed() = status == 200

    }
}