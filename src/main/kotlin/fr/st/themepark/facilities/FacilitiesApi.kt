package fr.st.themepark.facilities

import com.github.kittinunf.fuel.core.Method
import fr.st.themepark.DisneyApi
import fr.st.themepark.Resort

sealed class FacilitiesApi : DisneyApi() {
    
    class facilities(val resort : Resort) : FacilitiesApi() { }

    override val method = Method.POST
    
    override val path : String
        get() {
            return when(this) {
                is FacilitiesApi.facilities -> return "mobile-service/public/destinations/${this.resort.id};entityType\u003ddestination/facilities?region\u003dfr"
            }
        }
    override val params : List<Pair<String, Any?>>? = listOf()
}