package rodolfoizidoro.meucontato.api

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import rodolfoizidoro.meucontato.api.FirebaseConstants.CHECKED
import rodolfoizidoro.meucontato.api.FirebaseConstants.CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.DESCRIPTION
import rodolfoizidoro.meucontato.api.FirebaseConstants.DISPLAY_NAME
import rodolfoizidoro.meucontato.api.FirebaseConstants.EMAIL
import rodolfoizidoro.meucontato.api.FirebaseConstants.ID
import rodolfoizidoro.meucontato.api.FirebaseConstants.ID_SOCIAL
import rodolfoizidoro.meucontato.api.FirebaseConstants.NAME
import rodolfoizidoro.meucontato.api.FirebaseConstants.PHOTO
import rodolfoizidoro.meucontato.api.FirebaseConstants.PROFILES
import rodolfoizidoro.meucontato.api.FirebaseConstants.PROFILE_CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.TAG
import rodolfoizidoro.meucontato.api.FirebaseConstants.TYPE
import rodolfoizidoro.meucontato.api.FirebaseConstants.USER
import rodolfoizidoro.meucontato.api.FirebaseConstants.VALUE

class LoginRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    fun registerDataForNewUser(name: String, email: String): Task<Void> {
        val uid = auth.uid.toString()
        val user: HashMap<String, Any> = HashMap()
        val profiles: HashMap<String, Any> = HashMap()
        val contact: HashMap<String, Any> = HashMap()
        val contactsProfile: HashMap<String, Any> = HashMap()

        val ref: DocumentReference = database
            .collection(USER)
            .document(uid)

        //Create Contact
        contact[ID] = ref.collection(CONTACTS).document().id
        contact[TAG] = "gmail"
        contact[TYPE] = "email"
        contact[VALUE] = email

        //Create Link with Profiles
        contactsProfile[ID] = ref.collection(CONTACTS).document().id
        contactsProfile[CHECKED] = true
        contactsProfile[ID_SOCIAL] = contact["id"].toString()

        //Create user profile
        profiles[ID] = ref.collection(PROFILES).document().id
        profiles[NAME] = "Perfil 1"
        profiles[DISPLAY_NAME] = name
        profiles[PHOTO] = uid
        profiles[DESCRIPTION] = "Perfil de contato de $name"
        profiles[PROFILE_CONTACTS] = arrayListOf(contactsProfile)

        //Create User
        user[ID] = uid
        user[NAME] = name
        user[EMAIL] = email

        // Get a new write batch
        val batch = database.batch()

        batch.set(ref, user)
        batch.set(ref.collection(PROFILES).document(profiles[ID].toString()), profiles)
        batch.set(ref.collection(CONTACTS).document(contact[ID].toString()), contact)

        return batch.commit()
    }
}
