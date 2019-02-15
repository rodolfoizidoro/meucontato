package rodolfoizidoro.meucontato.model

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class MeetupEvent
    (
    var duration: Long?,
    var name: String,
    var time: Long,
    var yesRsvpCount: Int = 0,
    var eventUrl: String?,
    var description: String?,
    var group: Group,
    var photoUrl: String?,
    var venue: Venue?,
    var fee: Fee?
) : Serializable {


    val groupTitle: String
        get() = group.name


    val date: String
        get() {
            val sdfDate = SimpleDateFormat("dd/MM HH:mm")
            val now = Date(time)
            return sdfDate.format(now)
        }

    val dateDetail: String
        get() {
            val sdfDate = SimpleDateFormat("EEEE, d MMM", Locale("pt", "BR"))
            val now = Date(time!!)
            return sdfDate.format(now)
        }

    val dateTime: String
        get() {
            val sdfDate = SimpleDateFormat("HH:mm")
            val now = Date(time!!)
            return sdfDate.format(now)
        }

    val dateTimeDuration: String
        get() {
            val sdfDate = SimpleDateFormat("HH:mm")
            val now = Date(time!! + getDura())
            return sdfDate.format(now)
        }

    val venueName: String
        get() =  venue!!.name ?: "Não informado"

    val venueAddress: String
        get() =  venue!!.address1 ?: "Acesse mais informações para obter o local."

    val locationCoord: LatLng
        get() = if(hasLocationMap()) LatLng(venue!!.lat!!, venue!!.lon!!) else LatLng(0.0,0.0)

    fun getDura(): Long {
        return duration ?: 0L
    }

    fun getDesc(): String {
        return description ?: "n/a"
    }

    fun hasLocationMap(): Boolean {
        return venue != null && venue!!.lat != null && venue!!.lon != null
    }
}
