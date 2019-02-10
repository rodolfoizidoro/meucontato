package rodolfoizidoro.meucontato.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MeetupResponse(
    @SerializedName("results")
    var meetups: List<MeetupEvent>?
) : Serializable
