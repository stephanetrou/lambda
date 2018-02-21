package fr.st.themepark.authentication

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting
import fr.st.themepark.androidUserAgent

sealed class AuthenticationApi : FuelRouting {

    class accessToken() : AuthenticationApi() { }

    override val basePath = "https://authorization.go.com"
    override val path = "/token"
    override val method = Method.POST

    override val params = listOf("grant_type" to "assertion",
            "assertion_type" to "public",
            "client_id" to "WDPRO-MOBILE.MDX.WDW.ANDROID-PROD")

    override val headers : Map<String, String>? = mapOf(
            "user-agent" to androidUserAgent()
    )
}