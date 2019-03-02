package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class FilterCityViewModel(private val repository: MeetupRepository) : CoroutineViewModel() {

    private val mCityResponse: MutableLiveData<List<City>> = MutableLiveData()
    private val mError = LiveEvent<String>()

    fun cityResponse() = mCityResponse as LiveData<List<City>>
    fun error() = mError as LiveData<String>

    fun find(query: String) {
        jobs add launch {
            try {
                mCityResponse.value = (repository.findCity(query).await().cities)
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            }
        }
    }
}
