package rodolfoizidoro.meucontato.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.CityResponse
import rodolfoizidoro.meucontato.model.MeetupResponse

class MeetupRepository(private val service: MeetupService) {

    companion object {
        const val API_KEY = "51374c1150477771455c6642b7d5670"
    }

    suspend fun findCity(query: String): Deferred<CityResponse> {
        return withContext(IO) {
            async { service.findCity(API_KEY, query, 20, false).await() }
        }
    }

    suspend fun findEvent(query: String, city: City): Deferred<MeetupResponse> {
        return withContext(IO) {
            val fields = "group_key_photo, group_photo, group_category, photo_url"
            async { service.findEvents(API_KEY, query, city.lat, city.lon, 10, fields, 30, true).await() }
        }
    }
}
