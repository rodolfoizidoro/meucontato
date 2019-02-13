package rodolfoizidoro.meucontato.api

import retrofit2.Call
import rodolfoizidoro.meucontato.model.CityResponse

class MeetupRepository(private val service: MeetupService) {

    companion object {
        const val API_KEY = "51374c1150477771455c6642b7d5670"
    }

    fun findCity(query: String): Call<CityResponse> {
        return service.findCity(API_KEY, query, 20, false)
    }

}
