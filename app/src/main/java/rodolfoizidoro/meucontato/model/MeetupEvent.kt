package rodolfoizidoro.meucontato.model

import java.io.Serializable

class MeetupEvent
    (
    var duration: Long?,
    var name: String?,
    var time: Long?,
    var yesRsvpCount: Int = 0,
    var eventUrl: String?,
    var description: String?,
    var group: Group,
    var venue: Venue?,
    var fee: Fee?
) : Serializable {
//
//    val groupTitle: String?
//        get() = group!!.name
//
//    val photoLink: String?
//        get() = if (group!!.photo == null) null else group!!.photo!!.photoLink
//
//    val date: String
//        get() {
//            val sdfDate = SimpleDateFormat("dd/MM HH:mm")
//            val now = Date(time!!)
//            return sdfDate.format(now)
//        }
//
//    val dateDetail: String
//        get() {
//            val sdfDate = SimpleDateFormat("EEEE, d MMM", Locale("pt", "BR"))
//            val now = Date(time!!)
//            return sdfDate.format(now)
//        }
//
//    val dateTime: String
//        get() {
//            val sdfDate = SimpleDateFormat("HH:mm")
//            val now = Date(time!!)
//            return sdfDate.format(now)
//        }
//
//    val dateTimeDuration: String
//        get() {
//            val sdfDate = SimpleDateFormat("HH:mm")
//            val now = Date(time!! + getDuration()!!)
//            return sdfDate.format(now)
//        }
//
//    val venueName: String
//        get() = if (venue == null) "Não informado" else venue!!.name
//
//    val venueAddress: String
//        get() = if (venue == null) "Acesse mais informações para obter o local." else venue!!.address1
//
//    val locationCoordinates: LatLng
//        get() = LatLng(venue!!.lat, venue!!.lon)
//
//    fun getDuration(): Long? {
//        return if (duration == null) 0L else duration
//    }
//
//    fun getDescription(): String {
//        return if (description == null) "n/a" else description
//
//    }
//
//    fun hasLocationMap(): Boolean? {
//        return venue != null && venue!!.lat != null && venue!!.lon != null
//    }
}
