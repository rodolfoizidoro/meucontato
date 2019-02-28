package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ShareRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import java.lang.Exception

class ShareViewModel(private val repository: ShareRepository) : CoroutineViewModel() {

    private val profiles: MutableLiveData<List<Profile>> = MutableLiveData()
    private var shareId: MutableLiveData<String> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()
    private val saveSuccess: MutableLiveData<Void> = MutableLiveData()

    fun profiles() = profiles as LiveData<List<Profile>>
    fun shareId() = shareId as LiveData<String>
    fun error() = error as LiveData<String>
    fun saveSuccess() = saveSuccess as LiveData<Void>

    fun loadProfiles() {
        jobs add launch {
            try {
                profiles.value = repository.loadInfo().await()
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }


    fun shareProfile(position: Int) {
        jobs add launch {
            try {
                shareId.value = "${repository.getUserId()};${profiles.value!![position].id}"
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }


    fun saveContact(content: String) {
        val split = content.split(";")
        val user = split[0]
        val profile = split[1]

        jobs add launch {
            try {
                val contact = repository.findContact(user, profile).await()
                saveSuccess.value = repository.saveContact(contact).await()

            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }
}
