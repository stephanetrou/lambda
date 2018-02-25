package fr.st.themepark.handler

import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.github.kittinunf.fuel.Fuel
import fr.st.themepark.Park
import fr.st.themepark.authentication.public_auth
import fr.st.themepark.fuel.dynamodb.responseItem
import fr.st.themepark.saveItem
import fr.st.themepark.waittime.WaitTimesApi

class WaitTimesHandler : RequestHandler<Any?, ApiGatewayResponse> {

    override fun handleRequest(input: Any?, context: Context?): ApiGatewayResponse? = public_auth<ApiGatewayResponse?> {
        val park = Park.PARC_DISNEYLAND
        val timestamp = System.currentTimeMillis() / 1000
             
        val (request, response, waitTimes) = Fuel.Companion.request(WaitTimesApi.waitTimes(park)).responseString()
        val json = waitTimes.fold(success = { it }, failure = { "{ \"error\" : \"${response.responseMessage}\" }"})

        context?.logger?.log("Item : ${json}")

        json?.let{
            val item = Item.fromJSON(it)
            item.withLong("timestamp", timestamp)
            item.withString("id", "${park.id};$timestamp")
            saveItem(item)
        }
        ApiGatewayResponse(body=json)
    }
}