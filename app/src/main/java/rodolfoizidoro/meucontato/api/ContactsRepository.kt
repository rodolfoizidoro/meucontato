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

class ContactsRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    suspend fun loadInfo(): Deferred<List<Profile>> {
        return withContext(IO) {
            async {
                database
                    .collection(USER)
                    .document(auth.uid.toString())
                    .collection(SHARE)
                    .await(Profile::class.java)
            }
        }
    }
}
