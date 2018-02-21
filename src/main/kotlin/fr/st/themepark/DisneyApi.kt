package fr.st.themepark

import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.util.FuelRouting


abstract class DisneyApi : FuelRouting {

    override val basePath = "https://api.wdpro.disney.go.com"

    override val method = Method.GET

    override val headers = mutableMapOf(
            "X-User-Agent" to androidUserAgent(),
            "Accept" to "application/json;apiversion=1",
            "X-Conversation-Id" to "WDPRO-MOBILE.MDX.CLIENT-PROD",
            "X-App-Id" to "WDW-MDX-ANDROID-4.4.2",
            "X-Correlation-ID" to System.currentTimeMillis().toString()
    )
}