package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.ProfilesRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Profile

class ContactsViewModel(private val repository: ProfilesRepository) : CoroutineViewModel() {

    private val contacts: MutableLiveData<List<Profile>> = MutableLiveData()
    private val error: MutableLiveData<Exception> = MutableLiveData()

    fun contacts() = contacts as LiveData<List<Profile>>
    fun error() = error as LiveData<Exception>

    fun loadContacts() {
        jobs add launch {
            try {
                contacts.value = repository.loadInfo().await()
            } catch (e: Exception) {
                error.value = e
            }
        }
    }
}
