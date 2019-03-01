package rodolfoizidoro.meucontato.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import rodolfoizidoro.meucontato.api.FirebaseConstants.CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.PROFILES
import rodolfoizidoro.meucontato.api.FirebaseConstants.USER
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.await

class InfoDetailRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {
    private val ref = database.collection(USER).document(auth.uid.toString())

    suspend fun saveInfo(contact: Contact): Deferred<Void> {
        val map: HashMap<String, Any> = HashMap()
        map[FirebaseConstants.TYPE] = contact.type
        map[FirebaseConstants.TAG] = contact.tag
        map[FirebaseConstants.VALUE] = contact.value

        return withContext(IO) {
            async {
                val profiles = ref.collection(PROFILES).await(Profile::class.java)

                val batch = database.batch()

                batch.update(
                    ref.collection(CONTACTS)
                        .document(contact.id), map
                )

                for (profile in profiles) {
                    batch.update(
                        ref.collection(PROFILES).document(profile.id)
                            .collection(CONTACTS).document(contact.id), map
                    )
                }
                batch.commit().await()
            }
        }
    }
}
