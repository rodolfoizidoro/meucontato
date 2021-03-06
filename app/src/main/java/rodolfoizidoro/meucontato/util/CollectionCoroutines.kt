package rodolfoizidoro.meucontato.util

import com.google.firebase.firestore.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T : Any> CollectionReference.await(clazz: Class<T>): List<T> {
    return await { documentSnapshot -> documentSnapshot.toObject(clazz) as T }
}

suspend fun <T : Any> CollectionReference.await(parser: (documentSnapshot: DocumentSnapshot) -> T): List<T> {
    return suspendCancellableCoroutine { continuation ->
        get().addOnCompleteListener {
            if (it.isSuccessful && it.result != null) {
                val list = arrayListOf<T>()
                it.result?.forEach { list.add(parser.invoke(it)) }

                continuation.resume(list)
            } else if (it.exception != null){
                continuation.resumeWithException(it.exception!!)
            } else {
                continuation.resumeWithException(EmptyStackException())
            }
        }
    }
}
