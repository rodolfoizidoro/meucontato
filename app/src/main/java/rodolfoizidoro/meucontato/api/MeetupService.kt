package rodolfoizidoro.meucontato.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rodolfoizidoro.meucontato.model.CityResponse
import rodolfoizidoro.meucontato.model.MeetupResponse

interface MeetupService {
    @GET("/2/cities")
     fun findCity(
        @Query("key") key: String,
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("sign") sign: Boolean?
    ): Call<CityResponse>


    @GET("/2/open_events")
     fun findEvents(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("radius") radius: Int?,
        @Query("fields") fields: String,
        @Query("page") page: Int?,
        @Query("sign") sign: Boolean?
    ): Call<MeetupResponse>

//    @GET("/find/events")
//    fun rxFindEvents(
//        @Query("key") key: String,
//        @Query("lat") lat: Double?,
//        @Query("lon") lon: Double?,
//        @Query("radius") radius: Int?,
//        @Query("fields") fields: String,
//        @Query("sign") sign: Boolean?
//    ): Call<List<MeetupEvent>>
}
