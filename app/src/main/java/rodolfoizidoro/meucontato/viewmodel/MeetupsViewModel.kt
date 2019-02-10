package rodolfoizidoro.meucontato.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import org.koin.core.Koin
import rodolfoizidoro.meucontato.api.MeetupRepository

class MeetupsViewModel(val repository: MeetupRepository) : ViewModel() {

}
