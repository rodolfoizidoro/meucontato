package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.youse.forms.livedata.LiveDataForm
import br.com.youse.forms.livedata.models.LiveField
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.FirebaseConstants
import rodolfoizidoro.meucontato.api.InfoDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage
import rodolfoizidoro.meucontato.validators.RequiredValidator

class InfoDetailViewModel(private val repository: InfoDetailRepository, var contact: Contact) : CoroutineViewModel() {

    private val saveSuccess = MutableLiveData<Void>()
    private val saveError = LiveEvent<String>()
    private val dropDown = MutableLiveData<List<String>>()

    val spinner = listOf("Email", "Telefone", "Instagram")

    fun saveSucess() = saveSuccess as LiveData<Void>
    fun saveError() = saveError as LiveData<String>
    fun dropDown() = dropDown as LiveData<List<String>>

    fun saveInfo() {
        jobs add launch {
            try {
                saveSuccess.value = repository.saveInfo(contact).await()
            } catch (t: Throwable) {
                saveError.value = t.errorMessage()
            }
        }
    }

    fun loadDropDown() {
       dropDown.postValue(spinner)
    }
}
