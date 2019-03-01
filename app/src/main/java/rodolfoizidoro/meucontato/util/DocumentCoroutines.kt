package rodolfoizidoro.meucontato.util

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T : Any> DocumentReference.await(clazz: Class<T>): T {
    return await { documentSnapshot -> documentSnapshot.toObject(clazz)!! }
}

suspend fun <T : Any> DocumentReference.await(parser: (documentSnapshot: DocumentSnapshot) -> T): T {
    return suspendCancellableCoroutine { continuation ->
        get().addOnCompleteListener {

            try {
                if (it.isSuccessful && it.result != null) {
                    continuation.resume(parser.invoke(it.result!!))
                } else {
                    continuation.resumeWithException(it.exception!!)
                }
            } catch (e : Exception) {
                continuation.resumeWithException(e)
            }
        }
    }
}


suspend fun DocumentReference.updateAwait(var1: Map<String, Any>) {
    return suspendCancellableCoroutine { continuation ->
        update(var1).addOnCompleteListener {
            if (it.isSuccessful) {
                continuation.resume(Unit)
            } else {
                continuation.resumeWithException(it.exception!!)
            }
        }
    }
}
