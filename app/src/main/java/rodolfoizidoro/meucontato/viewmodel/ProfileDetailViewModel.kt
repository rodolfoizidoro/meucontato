package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfileDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile

class ProfileDetailViewModel(private val repository: ProfileDetailRepository) : CoroutineViewModel() {

    private val loadSuccess = MutableLiveData<Profile>()
    private val loadError = MutableLiveData<Exception>()

    fun loadSucess() = loadSuccess as LiveData<Profile>
    fun loadError() = loadError as LiveData<Exception>

    fun loadInfo(id: String) {
        jobs add launch {
            try {
                val contacts = repository.loadContacts(id).await()
                val profile = repository.loadProfile(id).await()
                profile.profileContacts = contacts

                loadSuccess.value = profile

            } catch (e: Exception) {
                loadError.value = e
            }
        }
    }
}
