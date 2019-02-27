package rodolfoizidoro.meucontato.viewmodel

import android.provider.Contacts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.youse.forms.livedata.LiveDataForm
import br.com.youse.forms.livedata.models.LiveField
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.FirebaseConstants
import rodolfoizidoro.meucontato.api.InfoDetailRepository
import rodolfoizidoro.meucontato.api.ProfileDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.validators.RequiredValidator

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
