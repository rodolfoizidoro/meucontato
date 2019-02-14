package rodolfoizidoro.meucontato.model

import java.io.Serializable

data class Group(val name: String?, val category: Category?, var photo: Photo?) : Serializable
