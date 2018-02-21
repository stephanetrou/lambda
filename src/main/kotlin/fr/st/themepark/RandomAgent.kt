package fr.st.themepark

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.jackson.mapper
import java.util.*

private const val resources = "useragent/useragent-data.json"
private val userAgent = mapper.readValue<Array<UserAgent>>(ClassLoader.getSystemResourceAsStream(resources))
private val random = Random(System.currentTimeMillis())

fun randomUserAgent(filter: (UserAgent) -> Boolean): String {
    val agents = userAgent.filter(filter)
    val index = random.nextInt(agents.size)

    return agents[index].userAgent
}

fun androidUserAgent() = randomUserAgent { it.osName == "Android" }
