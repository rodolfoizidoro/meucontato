package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfileDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile

class ProfileDetailViewModel(private val repository: ProfileDetailRepository) : CoroutineViewModel() {

    private val loadSuccess = MutableLiveData<Profile>()
    private val saveSuccess = MutableLiveData<Void>()
    private val loadError = MutableLiveData<Exception>()
    private val saveError = MutableLiveData<Exception>()

    fun loadSucess() = loadSuccess as LiveData<Profile>
    fun saveSuccess() = saveSuccess as LiveData<Void>
    fun loadError() = loadError as LiveData<Exception>
    fun saveError() = saveError as LiveData<Exception>

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

    fun saveProfile() {
        jobs add launch {
            try {
               saveSuccess.value = repository.saveProfile(loadSucess().value!!).await()
            } catch (e: Exception) {
                saveError.value = e
            }
        }
    }
}
