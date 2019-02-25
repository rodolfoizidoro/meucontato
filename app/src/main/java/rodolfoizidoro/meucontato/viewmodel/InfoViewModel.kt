package rodolfoizidoro.meucontato.viewmodel

import android.widget.TabHost
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.InfoRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Contact
import java.lang.Exception

class InfoViewModel(private val repository: InfoRepository) : CoroutineViewModel() {

    private val contacts: MutableLiveData<List<Contact>> = MutableLiveData()
    private val error: MutableLiveData<Exception> = MutableLiveData()

    fun contacts() = contacts as LiveData<List<Contact>>
    fun error() = error as LiveData<Exception>

    fun loadInfos() {
        jobs add launch {
            try {
                contacts.value = repository.loadInfo().await()
            } catch (e: Exception) {
                error.value = e
            }
        }
    }
}
