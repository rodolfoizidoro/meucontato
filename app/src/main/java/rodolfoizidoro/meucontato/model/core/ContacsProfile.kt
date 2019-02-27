package rodolfoizidoro.meucontato.model.core

import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class ContacsProfile(
    val id: String = "",
    val checked: Boolean = false,
    @set:PropertyName("id_social")
    @get:PropertyName("id_social")
    var idSocial: String = "") : Serializable
