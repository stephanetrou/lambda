package fr.st.themepark.calendar

import fr.st.themepark.DisneyApi
import fr.st.themepark.Resort
import fr.st.themepark.authentication.Mode
import fr.st.themepark.dateTimeFormatter
import java.time.LocalDate

sealed class CalendarApi : DisneyApi() {

    class calendar(val resort : Resort, val date : LocalDate = LocalDate.now(), val mode : Mode = Mode.DAY_SIMPLE, val region : String = "fr") : CalendarApi() { }

    override val path : String
        get() {
            return when(this) {
                is calendar -> return "mobile-service/public/finder/calendar/${this.resort.id};entityType\u003ddestination"
            }
        }
    override val params : List<Pair<String, Any?>>?
        get() {
            return when(this) {
                is calendar -> return listOf("date" to this.date.format(dateTimeFormatter),
                                             "mode" to this.mode.text,
                                             "region" to this.region)
            }
        }
}

data class ParkCalendar(val locations : Array<Location>)

data class Location(val title : String,
                    val urlFriendlyId : String,
                    val schedule : Schedules,
                    val webDetail : WebDetail,
                    val closedFacilities : Array<ClosedFacility> = arrayOf())

data class Schedules(val timeZone : String, val schedules : Array<Schedule>)

data class Schedule(val type : String, val date : String, val startTime : String, val endTime : String)

data class WebDetail(val href : String)

data class ClosedFacility(val id : String, val name : String, val urlFriendlyId: String, val refurbishment : Refurbishment)

data class Refurbishment(val schedule: ClosedSchedule)

data class ClosedSchedule(val schedules : Array<ClosedInterval>)

data class ClosedInterval(val startDate : String, val endDate : String)


