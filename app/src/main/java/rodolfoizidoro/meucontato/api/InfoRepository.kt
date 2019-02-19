package rodolfoizidoro.meucontato.api

import android.provider.ContactsContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import rodolfoizidoro.meucontato.api.FirebaseConstants.CONTACTS
import rodolfoizidoro.meucontato.api.FirebaseConstants.USER
import rodolfoizidoro.meucontato.model.core.Contact
import java.util.ArrayList

class InfoRepository(private val database: FirebaseFirestore, private val auth: FirebaseAuth) {

    fun loadInfo() : List<Contact>{
        val contacts: ArrayList<Contact> = ArrayList()

        database.collection(USER)
            .document(auth.uid.toString()).collection(CONTACTS).get()
            .addOnSuccessListener { querySnapshot ->
                val a = querySnapshot
                querySnapshot.documents.forEach { document ->
                    contacts.add(
                        document.toObject(Contact::class.java)!!
                    )
                }
            }.addOnFailureListener {
                throw it
            }

        return contacts
    }
}
