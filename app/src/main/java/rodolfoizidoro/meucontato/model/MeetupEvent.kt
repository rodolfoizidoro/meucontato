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
    var eventUrl: String,
    var description: String?,
    var group: Group,
    var photoUrl: String?,
    var venue: Venue?,
    var fee: Fee?
) : Serializable {

    fun groupTitle(): String = group.name
    fun dateEvent(): String {
        val sdfDate = SimpleDateFormat("dd/MM HH:mm")
        val now = Date(time)
        return sdfDate.format(now)
    }

    fun dateDetail(): String {
        val sdfDate = SimpleDateFormat("EEEE, d MMM", Locale("pt", "BR"))
        val now = Date(time!!)
        return sdfDate.format(now)
    }

    fun dateDetailTime(): String {
        return "${dateTime()} - ${dateTimeDuration()}"
    }

    private fun dateTime(): String {
        val sdfDate = SimpleDateFormat("HH:mm")
        val now = Date(time!!)
        return sdfDate.format(now)
    }

    private fun dateTimeDuration(): String {
        val sdfDate = SimpleDateFormat("HH:mm")
        val now = Date(time!! + durationTime())
        return sdfDate.format(now)
    }

    fun venueName(): String = venue?.name ?: "Não informado"

    fun venueAddress(): String = venue?.address1 ?: "Acesse mais informações para obter o local."

    fun locationCoord(): LatLng = if (hasLocationMap()) LatLng(venue?.lat!!, venue?.lon!!) else LatLng(0.0, 0.0)

    private fun durationTime(): Long {
        return duration ?: 0L
    }

    fun eventDescription(): String {
        return description ?: "n/a"
    }

    fun hasLocationMap(): Boolean {
        return venue != null && venue!!.lat != null && venue!!.lon != null
    }

    fun hasFee(): Boolean {
        return fee != null
    }
}
