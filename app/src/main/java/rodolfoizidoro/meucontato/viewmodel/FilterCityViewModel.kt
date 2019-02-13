package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import br.com.wellingtoncosta.coroutines.ui.base.CoroutineViewModel
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.CityResponse

class FilterCityViewModel(private val repository: MeetupRepository) : CoroutineViewModel() {

    private val cityResponse: MutableLiveData<List<City>> = MutableLiveData()
    var d: List<City>? = emptyList()

    fun cityResponse() = cityResponse as LiveData<List<City>>

    fun find(city: String) {
        jobs add launch {
            try {
                cityResponse.value = (repository.findCity("Rio").await().cities)

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
