package fr.st.themepark.time

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDate.of
import java.time.ZoneId

enum class ScholarZone {
    ZONE_A, ZONE_B, ZONE_C
}

val schoolVacations = mapOf(
        ScholarZone.ZONE_A to listOf(
                of(2017, 10, 21 ) .. of(2017, 11,5 ),
                of(2017, 12,23) .. of(2018, 1,8),
                of(2018,2,10) .. of(2018,2,25),
                of(2018,4,7) .. of(2018,4,22),
                of(2018,7,7) .. of(2018,9,2)
        ),
        ScholarZone.ZONE_B to listOf(
                of(2017, 10, 21 ) .. of(2017, 11,5 ),
                of(2017, 12,23) .. of(2018, 1,8),
                of(2018,2,24) .. of(2018,3,11),
                of(2018,4,21) .. of(2018,5,6),
                of(2018,7,7) .. of(2018,9,2)
        ),
        ScholarZone.ZONE_C to listOf(
                of(2017, 10, 21 ) .. of(2017, 11,5 ),
                of(2017, 12,23) .. of(2018, 1,8),
                of(2018,2,17) .. of(2018,3,4),
                of(2018,4,14) .. of(2018,4,30),
                of(2018,7,7) .. of(2018,9,2)
        )
)

fun generateInfo(date : LocalDate = LocalDate.now()) {
    println(date.dayOfMonth)
    println(date.dayOfWeek.value)
    println(date.dayOfYear)
    println(date.monthValue)
    println(date.year)
    println(date.atStartOfDay(ZoneId.of("Europe/Paris")).toEpochSecond())
    println(date.plusDays(1).atStartOfDay(ZoneId.of("Europe/Paris")).toEpochSecond() - 1)
    println(date.dayOfWeek in DayOfWeek.SATURDAY .. DayOfWeek.SUNDAY)
    ScholarZone.values().forEach {
        val v = schoolVacations(it, date)
        println("${it.name} : $v")
    }

}

fun schoolVacations(zone : ScholarZone, date : LocalDate) : Boolean {
    return schoolVacations[zone]?.map { date in it}?.max() ?: false
}

fun main(args: Array<String>) {
    generateInfo()
}