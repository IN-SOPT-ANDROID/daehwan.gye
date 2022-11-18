package org.sopt.sample

enum class ApiUrl(
    val hostname: String,
    val port: Int
) {
    LOCALHOST("10.0.2.2", 8080),
    SOPT("3.39.169.52", 3000),
    REQRES("reqres.in", 443)
    ;

    /**
     * abstract functions
     */
    fun asHttp() = "${Protocol.HTTP.type}${domain()}"
    fun asHttps() = "${Protocol.HTTPS.type}${domain()}"

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