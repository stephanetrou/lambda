package fr.st.themepark

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalTime

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class AccessToken(val accessToken: String,
                       val tokenType: String,
                       val scope: String,
                       val expiresIn: Long)

data class UserAgent(val folder: String,
        val description: String,
        val userAgent: String,
        val appCodename: String?,
        val appName: String,
        val appVersion: String,
        val platform: String,
        val vendor: String,
        val vendorSub: String,
        val browserName: String,
        val browserMajor: String,
        val browserVersion: String,
        val deviceModel: String,
        val deviceType: String,
        val deviceVendor: String,
        val engineName: String,
        val engineVersion: String,
        val osName: String,
        val osVersion: String,
        val cpuArchitecture: String)

data class RideWaitTime(val id : String, val waitTime: WaitTime )

data class FastPass( val startTime : LocalTime?, val endTime : LocalTime?, val available : Boolean)

data class WaitTime(val fastPass : FastPass,
                    val status: String,
                    val singleRide : Boolean,
                    val postedWaitMinutes: Int,
                    val rollUpStatus: String?,
                    val rollUpWaitTimeMessage: String?)