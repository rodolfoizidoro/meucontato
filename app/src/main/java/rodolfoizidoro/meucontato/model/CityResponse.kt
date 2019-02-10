package rodolfoizidoro.meucontato.model

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("results")
    val cities: List<City>
)

data class City(
    val city: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("localized_country_name")
    val country: String
)
