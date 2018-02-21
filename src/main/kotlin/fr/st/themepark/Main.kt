package fr.st.themepark

import com.amazonaws.services.dynamodbv2.document.Item
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.jackson.responseObject
import com.github.kittinunf.result.Result
import fr.st.themepark.authentication.AuthenticationApi
import fr.st.themepark.calendar.CalendarApi
import fr.st.themepark.facilities.FacilitiesApi
import fr.st.themepark.schedule.ScheduleApi
import fr.st.themepark.waittime.WaitTimesApi

fun main(args: Array<String>) {
    public_auth {
        val park = Park.DISNEYLAND_RESORT_CALIFORNIA_ADVENTURE
        val resort = park.resort

        val (_, _, calendar) = Fuel.request(CalendarApi.calendar(resort)).responseString()
        calendar.fold(
                success = { val item = Item.fromJSON(it)
                            item.withLong("timestamp", System.currentTimeMillis() / 1000)
                            println("Success : $item") },
                failure = { println("Error : $it") }
        )

        val (_, _, waitTimes) = Fuel.request(WaitTimesApi.waitTimes(park)).responseString()
        waitTimes.fold(
                success = { println("Success : $it")},
                failure = { println("Error : $it") }
        )

        val (_, _, facilities) = Fuel.request(FacilitiesApi.facilities(resort)).responseString()
        facilities.fold(
                success = { println("Success : ")},
                failure = { println("Error : $it") }
        )

        val (_, _, schedules) = Fuel.request(ScheduleApi.schedule(resort)).responseString()
        schedules.fold(
                success = { println("Success : $it")},
                failure = { println("Error : $it") }
        )
    }
}

fun public_auth(fct : () -> Any?) {

    val (_, _, result) = Fuel.request(AuthenticationApi.accessToken()).responseObject<AccessToken>()

    var interceptor : ((Request) -> Request)? = null

    when (result) {
        is Result.Success -> {
            interceptor = authorizationRequestInterceptor(result.value)
            FuelManager.instance.addRequestInterceptor { interceptor }
        }
    }

    fct.invoke()

    if (interceptor != null) {
        FuelManager.instance.removeRequestInterceptor { interceptor }
    }
}

fun authorizationRequestInterceptor(accessToken : AccessToken) : (Request) -> Request {
    return {
        val authorization = "${accessToken.tokenType} ${accessToken.accessToken}"
        it.header("Authorization" to authorization)
    }
}

