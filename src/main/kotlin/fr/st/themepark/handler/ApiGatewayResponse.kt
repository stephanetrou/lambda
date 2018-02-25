package fr.st.themepark.handler


data class ApiGatewayResponse(val statusCode : Int = 200,
                              val body : Any?,
                              val headers : Map<String, String> = mapOf(),
                              val isBase64Encoded : Boolean = false)