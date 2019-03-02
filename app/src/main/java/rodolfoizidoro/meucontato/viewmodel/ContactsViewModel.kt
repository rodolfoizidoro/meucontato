package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ContactsRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class ContactsViewModel(private val repository: ContactsRepository) : CoroutineViewModel() {

    private val mContacts: MutableLiveData<List<Profile>> = MutableLiveData()
    private val mError = LiveEvent<String>()

    fun contacts() = mContacts as LiveData<List<Profile>>
    fun error() = mError as LiveData<String>

    fun loadContacts() {
        jobs add launch {
            try {
                mContacts.value = repository.loadInfo().await()
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }
}
