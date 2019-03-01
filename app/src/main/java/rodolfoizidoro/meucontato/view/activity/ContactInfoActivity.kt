package rodolfoizidoro.meucontato.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityContactInfoBinding
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.viewmodel.ContactInfoViewModel

class ContactInfoActivity : BaseActivity() {

    companion object {
        const val EXTRA_CONTACT_INFO = "extra_contact_info"
    }

    private val viewModel: ContactInfoViewModel by viewModel { parametersOf(getExtraContactInfo()) }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityContactInfoBinding>(this, R.layout.activity_contact_info)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        prepareToolbar()
    }


    private fun getExtraContactInfo() : Profile {
        return intent.getSerializableExtra(EXTRA_CONTACT_INFO) as Profile
    }
}
