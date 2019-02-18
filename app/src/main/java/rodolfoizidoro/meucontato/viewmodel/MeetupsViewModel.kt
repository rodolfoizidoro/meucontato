package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.MeetupEvent

class MeetupsViewModel(private val repository: MeetupRepository) : CoroutineViewModel()  {


    private val response: MutableLiveData<List<MeetupEvent>> = MutableLiveData()
    fun meetupResponse() = response as LiveData<List<MeetupEvent>>

    fun find(query: String, city: City) {
        jobs add launch {
            try {
                response.value = (repository.findEvent(query, city).await().meetups)
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
