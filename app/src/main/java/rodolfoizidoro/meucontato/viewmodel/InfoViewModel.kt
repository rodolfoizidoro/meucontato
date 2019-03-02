package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.InfoRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage
import java.lang.Exception

class InfoViewModel(private val repository: InfoRepository) : CoroutineViewModel() {

    private val mContacts: MutableLiveData<List<Contact>> = MutableLiveData()
    private val mError = LiveEvent<String>()

    fun contacts() = mContacts as LiveData<List<Contact>>
    fun error() = mError as LiveData<String>

    fun loadInfos() {
        jobs add launch {
            try {
                mContacts.value = repository.loadInfo().await()
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }
}
