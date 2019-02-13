package rodolfoizidoro.meucontato.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Call
import rodolfoizidoro.meucontato.model.CityResponse

class MeetupRepository(private val service: MeetupService) {

    companion object {
        const val API_KEY = "51374c1150477771455c6642b7d5670"
    }

    suspend fun findCity(query: String): Deferred<CityResponse> {
        return withContext(IO) {
            async { service.findCity(API_KEY, query, 20, false).await() }
        }
    }
}
