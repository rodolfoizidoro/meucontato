package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfilesRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class ProfilesViewModel(private val repository: ProfilesRepository) : CoroutineViewModel() {

    private val mProfiles: MutableLiveData<List<Profile>> = MutableLiveData()
    private val mError = LiveEvent<String>()

    fun profiles() = mProfiles as LiveData<List<Profile>>
    fun error() = mError as LiveData<String>

    fun loadProfiles() {
        jobs add launch {
            try {
                mProfiles.value = repository.loadInfo().await()
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }
}
