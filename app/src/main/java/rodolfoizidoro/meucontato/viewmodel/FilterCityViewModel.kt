package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.model.City

class FilterCityViewModel(private val repository: MeetupRepository) : CoroutineViewModel() {

    private val cityResponse: MutableLiveData<List<City>> = MutableLiveData()
    fun cityResponse() = cityResponse as LiveData<List<City>>

    fun find(query: String) {
        jobs add launch {
            try {
                cityResponse.value = (repository.findCity(query).await().cities)
            } catch (t: Throwable) {

                val erro = t
                val erro2 = t

            } finally {
                val b = ""
                val c = ""
            }
        }
    }
}
