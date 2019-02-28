package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfileDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile

class ProfileDetailViewModel(private val repository: ProfileDetailRepository) : CoroutineViewModel() {

    private val mLoadSuccess = MutableLiveData<Profile>()
    private val mSaveSuccess = MutableLiveData<Void>()
    private val mLoadError = MutableLiveData<Exception>()
    private val mSaveError = MutableLiveData<Exception>()
    private val mProgress = MutableLiveData<Boolean>()

    fun loadSucess() = mLoadSuccess as LiveData<Profile>
    fun saveSuccess() = mSaveSuccess as LiveData<Void>
    fun loadError() = mLoadError as LiveData<Exception>
    fun saveError() = mSaveError as LiveData<Exception>
    fun progress() = mProgress as LiveData<Boolean>

    fun loadInfo(id: String) {
        jobs add launch {
            try {
                mProgress.value = true
                val contacts = repository.loadContacts(id).await()
                val profile = repository.loadProfile(id).await()
                profile.profileContacts = contacts

                mLoadSuccess.value = profile

            } catch (e: Exception) {
                mLoadError.value = e
            } finally {
                mProgress.value = false
            }
        }
    }

    fun saveProfile() {
        jobs add launch {
            try {
                mSaveSuccess.value = repository.saveProfile(loadSucess().value!!).await()
            } catch (e: Exception) {
                mSaveError.value = e
            }
        }
    }
}
