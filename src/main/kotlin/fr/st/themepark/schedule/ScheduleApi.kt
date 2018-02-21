package fr.st.themepark.schedule

import fr.st.themepark.DisneyApi
import fr.st.themepark.Resort
import fr.st.themepark.Type
import fr.st.themepark.dateTimeFormatter
import java.time.LocalDate


sealed class ScheduleApi : DisneyApi() {

    class schedule(val resort : Resort, vararg val types : Type = arrayOf(Type.THEME_PARK, Type.ATTRACTION), val startDate : LocalDate = LocalDate.now(), val endDate : LocalDate = LocalDate.now()) : ScheduleApi() { }

    override val path: String
        get() {
            return when(this) {
                is ScheduleApi.schedule -> "mobile-service/public/ancestor-activities-schedules/${this.resort.id};entityType\u003ddestination"
            }
        }
    override val params: List<Pair<String, Any?>>?
        get() {
            return when(this) {
                // "resort,theme-park,water-park,land,Entertainment-Venue,Attraction,Entertainment,Event,Spa,tour,recreation-activity,Recreation,restaurant,MerchandiseFacility"
                is ScheduleApi.schedule -> listOf(
                        "filters" to types.joinToString(separator = ",", transform = { it.type} ),
                        "startDate" to this.startDate.format(dateTimeFormatter),
                        "endDate" to this.endDate.format(dateTimeFormatter),
                        "region" to this.resort.region

                )
            }
        }

}