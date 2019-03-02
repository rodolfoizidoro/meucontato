package rodolfoizidoro.meucontato.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Venue : Serializable {
    var name: String? = null
    var lat: Double? = null
    var lon: Double? = null
    @SerializedName("address_1")
    var address1: String? = null
    var city: String? = null
}
