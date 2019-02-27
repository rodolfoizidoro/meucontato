package rodolfoizidoro.meucontato.model.core

import com.google.firebase.firestore.PropertyName
import java.io.Serializable

class Profile(var description: String = "",
              @set:PropertyName("display_name")
              @get:PropertyName("display_name")
              var displayName: String = "",
              val id: String = "",
              var name : String = "",
              val photo : String = "",
              var profileContacts : List<Contact> = emptyList()
              ) : Serializable
