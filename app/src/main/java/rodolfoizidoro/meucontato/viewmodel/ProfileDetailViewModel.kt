package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfileDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class ProfileDetailViewModel(private val repository: ProfileDetailRepository) : CoroutineViewModel() {

    private val mLoadSuccess = LiveEvent<Profile>()
    private val mLoadError = LiveEvent<String>()
    private val mSaveSuccess = LiveEvent<Void>()
    private val mSaveError = LiveEvent<String>()
    private val mProgress = LiveEvent<Boolean>()

    fun loadSucess() = mLoadSuccess as LiveData<Profile>
    fun saveSuccess() = mSaveSuccess as LiveData<Void>
    fun loadError() = mLoadError as LiveData<String>
    fun saveError() = mSaveError as LiveData<String>
    fun progress() = mProgress as LiveData<Boolean>

    fun loadInfo(id: String) {
        jobs add launch {
            try {
                mProgress.value = true
                val contacts = repository.loadContacts(id).await()
                val profile = repository.loadProfile(id).await()
                profile.profileContacts = contacts

                mLoadSuccess.value = profile
            } catch (t: Throwable) {
                mLoadError.value = t.errorMessage()
            } finally {
                mProgress.value = false
            }
        }
    }

    fun saveProfile() {
        jobs add launch {
            try {
                mSaveSuccess.value = repository.saveProfile(loadSucess().value!!).await()
            } catch (t: Throwable) {
                mSaveError.value = t.errorMessage()
            }
        }
    }
}
