package org.sopt.sample

enum class ApiUrl(
    val hostname: String,
    val port: Int
) {
    LOCALHOST("10.0.2.2", 8080) {
        override fun asHttp() = "${Protocol.HTTP.type}${domain()}"
        override fun asHttps() = "${Protocol.HTTPS.type}${domain()}"
    },
    SOPT("3.39.169.52", 3000) {
        override fun asHttp(): String = "${Protocol.HTTP.type}${domain()}"

        override fun asHttps(): String = "${Protocol.HTTPS.type}${domain()}"
    }
    ;

    /**
     * abstract functions
     */
    abstract fun asHttp(): String  // http://localhost:8080/
    abstract fun asHttps(): String   // https://localhost:8080/

    /**
     *  implemented functions
     */
    fun domain() = "$hostname:$port/"

    /**
     * internal classes
     */
    private enum class Protocol(
        val type: String
    ) {
        HTTP("http://"),
        HTTPS("https://"),
    }
}