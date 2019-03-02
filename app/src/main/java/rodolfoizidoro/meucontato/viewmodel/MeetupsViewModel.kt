package rodolfoizidoro.meucontato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.util.LiveEvent
import rodolfoizidoro.meucontato.util.errorMessage

class MeetupsViewModel(private val repository: MeetupRepository) : CoroutineViewModel() {

    private val mResponse: MutableLiveData<List<MeetupEvent>> = MutableLiveData()
    private val mProgress: MutableLiveData<Boolean> = MutableLiveData()
    private val mError = LiveEvent<String>()

    fun meetupResponse() = mResponse as LiveData<List<MeetupEvent>>
    fun progress() = mProgress as LiveData<Boolean>
    fun error() = mError as LiveData<String>

    fun find(query: String, city: City) {
        jobs add launch {
            try {
                mProgress.value = true
                mResponse.value = (repository.findEvent(query, city).await().meetups)
            } catch (t: Throwable) {
                mError.value = t.errorMessage()
            } finally {
                mProgress.value = false
            }
        }
    }

}
