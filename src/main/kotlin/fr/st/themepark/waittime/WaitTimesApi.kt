package fr.st.themepark.waittime

import fr.st.themepark.DisneyApi
import fr.st.themepark.Park

sealed class WaitTimesApi : DisneyApi() {

    class waitTimes(val park : Park) : WaitTimesApi() { }

    override val path : String
            get() {
                return when(this) {
                    // "facility-service/theme-parks/${this.park.id}/wait-times"
                    is WaitTimesApi.waitTimes -> return "facility-service/theme-parks/${this.park.id};destination\u003d${this.park.resort.id}/wait-times"
                }
            }
    override val params : List<Pair<String, Any?>>?
        get() {
            return when(this) {
                is WaitTimesApi.waitTimes -> return listOf("mobile" to "true", "region" to "${this.park.resort.region}")
            }
        }
}