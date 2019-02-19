package rodolfoizidoro.meucontato.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class LoginRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    suspend fun registerDataForNewUser(name: String, email: String){
        withContext(IO) {
            val uid = auth.uid.toString()
            val user: HashMap<String, Any> = HashMap()
            val profiles: HashMap<String, Any> = HashMap()
            val contact: HashMap<String, Any> = HashMap()
            val contactsProfile: HashMap<String, Any> = HashMap()

            //Create Contact
            contact["id"] = database.collection("user").document(uid).collection("contacts").document().id
            contact["tag"] = "gmail"
            contact["type"] = "email"
            contact["value"] = email

            //Create Link with Profiles
            contactsProfile["id"] = database.collection("user").document(uid).collection("contacts").document().id
            contactsProfile["checked"] = true
            contactsProfile["id_social"] = contact["id"].toString()

            //Create user profile
            profiles["id"] = database.collection("user").document(uid).collection("profiles").document().id
            profiles["name"] = "Perfil 1"
            profiles["display_name"] = name
            profiles["photo"] = uid
            profiles["description"] = "Perfil de contato de $name"
            profiles["profile_contacts"] = arrayListOf(contactsProfile)

            //Create User
            user["id"] = uid
            user["name"] = name
            user["email"] = email
            user["profiles"] = arrayListOf(profiles)
            user["contacts"] = arrayListOf(contact)

            FirebaseFirestore.getInstance()
                .collection("user").document(uid).set(user)
                .addOnFailureListener { exception ->
                    throw exception
                }
        }
    }
}
