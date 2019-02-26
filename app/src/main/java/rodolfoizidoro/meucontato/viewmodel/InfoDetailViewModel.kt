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
import rodolfoizidoro.meucontato.validators.RequiredValidator

class InfoDetailViewModel(private val repository: InfoDetailRepository, var contact: Contact) : CoroutineViewModel() {

    private val saveSuccess = MutableLiveData<Unit>()
    private val saveError = MutableLiveData<Exception>()

    val tag = LiveField(key = FirebaseConstants.TAG, validators = listOf(RequiredValidator("Campo tag requerido")))
    val value = LiveField(key = FirebaseConstants.VALUE, validators = listOf(RequiredValidator("Campo contato requerido")))
    val form = LiveDataForm.Builder<String>()
        .addField(tag)
        .addField(value)
        .build()

    fun saveSucess() = saveSuccess as LiveData<Unit>
    fun saveError() = saveError as LiveData<Exception>

    fun saveInfo() {
        jobs add launch {
            try {
                saveSuccess.value = repository.saveInfo(contact).await()
            } catch (e: Exception) {
                saveError.value = e
            }
        }
    }
}
