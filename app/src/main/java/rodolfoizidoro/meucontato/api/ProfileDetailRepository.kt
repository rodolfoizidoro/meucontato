package rodolfoizidoro.meucontato.api

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
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
import rodolfoizidoro.meucontato.util.updateAwait

class ProfileDetailRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    private val ref = database.collection(USER).document(auth.uid.toString()).collection(PROFILES)

    suspend fun loadProfile(id: String): Deferred<Profile> {
        return withContext(IO) {
            async {
                ref.document(id).await(Profile::class.java)
            }
        }
    }

    suspend fun loadContacts(id: String): Deferred<List<Contact>> {
        return withContext(IO) {
            async {
                ref.document(id).collection(CONTACTS).await(Contact::class.java)
            }
        }
    }
}
