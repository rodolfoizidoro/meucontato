package rodolfoizidoro.meucontato.viewmodel

import rodolfoizidoro.meucontato.api.InfoDetailRepository
import rodolfoizidoro.meucontato.common.CoroutineViewModel
import rodolfoizidoro.meucontato.model.core.Contact

class InfoDetailViewModel(val repository: InfoDetailRepository, val contact : Contact? = null) : CoroutineViewModel() {
}
