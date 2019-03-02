package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rodolfoizidoro.meucontato.api.LoginRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage
import java.lang.Exception

class LoginViewModel(private val repository: LoginRepository) : CoroutineViewModel() {

    private val mSuccess = LiveEvent<Void>()
    private val mError = LiveEvent<String>()

    fun success() = mSuccess as LiveData<Void>
    fun error() = mError as LiveData<String>

    fun registerDataForNewUser(name: String, email: String) {
        repository.registerDataForNewUser(name, email)
            .addOnSuccessListener {
                mSuccess.value = it
            }
            .addOnFailureListener {
                mError.value = it.errorMessage()
            }

    }
}
