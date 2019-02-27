package rodolfoizidoro.meucontato.model.core

import java.io.Serializable

data class Contact(var id: String = "", var tag: String = "", var type: String = "", var value: String = "") :
    Serializable {
    var checked: Boolean = false
}
