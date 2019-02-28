package rodolfoizidoro.meucontato.api

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import rodolfoizidoro.meucontato.api.FirebaseConstants.CHECKED
import rodolfoizidoro.meucontato.api.FirebaseConstants.CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.DESCRIPTION
import rodolfoizidoro.meucontato.api.FirebaseConstants.DISPLAY_NAME
import rodolfoizidoro.meucontato.api.FirebaseConstants.NAME
import rodolfoizidoro.meucontato.api.FirebaseConstants.PROFILES
import rodolfoizidoro.meucontato.api.FirebaseConstants.TAG
import rodolfoizidoro.meucontato.api.FirebaseConstants.USER
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.await

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

    suspend fun saveProfile(profile: Profile): Deferred<Void> {
        return withContext(IO) {
            async {
                val mapProfile: HashMap<String, Any> = HashMap()
                val batch = database.batch()
                val refProfile = ref.document(profile.id)

                mapProfile[NAME] = profile.name
                mapProfile[DESCRIPTION] = profile.description
                mapProfile[DISPLAY_NAME] = profile.displayName

                batch.update(refProfile, mapProfile)

                profile.profileContacts.forEach {
                    batch.update(refProfile.collection(CONTACTS).document(it.id), CHECKED, it.checked)
                }
                batch.commit().await()
            }
        }
    }
}
