package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import rodolfoizidoro.meucontato.api.LoginRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import java.lang.Exception

class LoginViewModel(private val repository: LoginRepository) : CoroutineViewModel() {

    private val success: MutableLiveData<Void> = MutableLiveData()
    private val error: MutableLiveData<Exception> = MutableLiveData()

    fun success() = success as LiveData<Void>
    fun error() = error as LiveData<Exception>

    fun registerDataForNewUser(name: String, email: String) {
        repository.registerDataForNewUser(name, email)
            .addOnSuccessListener {
                success.value = it
            }
            .addOnFailureListener {
                error.value = it
            }

    }
}
