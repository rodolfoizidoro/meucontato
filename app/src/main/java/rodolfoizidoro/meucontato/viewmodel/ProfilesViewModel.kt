package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfilesRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import java.lang.Exception

class ProfilesViewModel(private val repository: ProfilesRepository) : CoroutineViewModel() {

    private val profiles: MutableLiveData<List<Profile>> = MutableLiveData()
    private val error: MutableLiveData<Exception> = MutableLiveData()

    fun profiles() = profiles as LiveData<List<Profile>>
    fun error() = error as LiveData<Exception>

    fun loadProfiles() {
        jobs add launch {
            try {
                profiles.value = repository.loadInfo().await()

            } catch (e: Exception) {
                error.value = e
            }
        }
    }
}
