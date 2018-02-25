package fr.st.themepark.authentication

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.jackson.responseObject
import com.github.kittinunf.result.Result
import fr.st.themepark.AccessToken


fun <T> public_auth(fct : () -> T?) : T? {
    var response : T? = null

    val (_, _, result) = Fuel.request(AuthenticationApi.accessToken()).responseObject<AccessToken>()

    when (result) {
        is Result.Success -> {
            val interceptor = authorizationRequestInterceptor(result.value)
            FuelManager.instance.addRequestInterceptor (interceptor)

            response = fct.invoke()

            FuelManager.instance.removeRequestInterceptor(interceptor)
        }
    }

    return response
}

fun authorizationRequestInterceptor(accessToken : AccessToken) = {
    next : (Request) -> Request -> {
        r : Request ->
                val authorization = "${accessToken.tokenType} ${accessToken.accessToken}"
                next(r.header("Authorization" to authorization))
        }
}

