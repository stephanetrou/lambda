package fr.st.themepark

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.jackson.mapper


fun main(args: Array<String>) {
    val toto = """{
      "id": "15822029;entityType=Attraction",
      "waitTime": {
        "fastPass": {
          "startTime": "19:05:00",
          "endTime": "20:05:00",
          "available": true
        },
        "status": "Operating",
        "singleRider": false,
        "postedWaitMinutes": 40,
        "rollUpStatus": "Operating",
        "rollUpWaitTimeMessage": "Moderate Wait Times"
      }
    }"""

    println(mapper.readValue<RideWaitTime>(toto))
}