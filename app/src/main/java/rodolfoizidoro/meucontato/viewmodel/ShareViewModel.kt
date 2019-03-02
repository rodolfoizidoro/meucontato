package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ShareRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class ShareViewModel(private val repository: ShareRepository) : CoroutineViewModel() {

    private val mProfiles: MutableLiveData<List<Profile>> = MutableLiveData()
    private val mError = LiveEvent<String>()
    private val mSaveSuccess = LiveEvent<Void>()
    private var mShareId = ""

    fun profiles() = mProfiles as LiveData<List<Profile>>
    fun shareId() = mShareId
    fun error() = mError as LiveData<String>
    fun saveSuccess() = mSaveSuccess as LiveData<Void>

    fun loadProfiles() {
        jobs add launch {
            try {
                val profileList = repository.loadInfo().await()
                mProfiles.value = profileList
                mShareId = "${repository.getUserId()};${profileList[0].id}"
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }

    fun shareProfile(position: Int) {
        jobs add launch {
            try {
                mShareId = "${repository.getUserId()};${mProfiles.value!![position].id}"
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
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
                mSaveSuccess.value = repository.saveContact(contact).await()

            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }
}
