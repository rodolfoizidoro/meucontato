package rodolfoizidoro.meucontato.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CityResponse(
    @SerializedName("results")
    val cities: List<City>
)

data class City(
    @SerializedName("city")
    val name: String,
    val lat: Double,
    val lon: Double,
    @SerializedName("localized_country_name")
    val country: String
) : Serializable {

    fun fullName() = "$name, $country"
}
