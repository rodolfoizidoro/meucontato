package rodolfoizidoro.meucontato.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import rodolfoizidoro.meucontato.api.FirebaseConstants.CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.PROFILES
import rodolfoizidoro.meucontato.api.FirebaseConstants.SHARE
import rodolfoizidoro.meucontato.api.FirebaseConstants.USER
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.await

class ShareRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    suspend fun loadInfo(): Deferred<List<Profile>> {
        return withContext(IO) {
            async {
                database
                    .collection(USER)
                    .document(auth.uid.toString())
                    .collection(PROFILES)
                    .await(Profile::class.java)
            }
        }
    }

    suspend fun findContact(user: String, id: String): Deferred<Profile> {
        return withContext(IO) {
            async {
                val ref = database.collection(USER).document(user).collection(PROFILES)
                val profile = ref.document(id).await(Profile::class.java)
                val contacts = ref.document(id).collection(CONTACTS).await(Contact::class.java)
                profile.profileContacts = contacts
                profile
            }
        }
    }


    suspend fun saveContact(profile: Profile): Deferred<Void> {
        return withContext(IO) {
            async {
                database
                    .collection(USER)
                    .document(auth.uid.toString())
                    .collection(SHARE)
                    .document(profile.id)
                    .set(profile).await()
            }
        }
    }

    fun getUserId(): String = auth.uid.toString()
}
