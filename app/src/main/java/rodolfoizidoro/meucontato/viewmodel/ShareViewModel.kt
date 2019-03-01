package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ShareRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.LiveEvent
import java.lang.Exception

class ShareViewModel(private val repository: ShareRepository) : CoroutineViewModel() {

    private val profiles: MutableLiveData<List<Profile>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()
    private val saveSuccess = LiveEvent<Void>()
    private var shareId = ""

    fun profiles() = profiles as LiveData<List<Profile>>
    fun shareId() = shareId
    fun error() = error as LiveData<String>
    fun saveSuccess() = saveSuccess as LiveData<Void>

    fun loadProfiles() {
        jobs add launch {
            try {
                val profileList = repository.loadInfo().await()
                profiles.value = profileList
                shareId = "${repository.getUserId()};${profileList[0].id}"
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }


    fun shareProfile(position: Int) {
        jobs add launch {
            try {
                shareId = "${repository.getUserId()};${profiles.value!![position].id}"
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }


    fun saveContact(content: String) {
        jobs add launch {
            try {
                val split = content.split(";")
                val user = split[0]
                val profile = split[1]

                val contact = repository.findContact(user, profile).await()
                saveSuccess.value = repository.saveContact(contact).await()

            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }
}
