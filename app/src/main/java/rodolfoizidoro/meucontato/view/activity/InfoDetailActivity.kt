package rodolfoizidoro.meucontato.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_info_detail.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityInfoDetailBinding
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.viewmodel.InfoDetailViewModel

class InfoDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
    }

    private val viewModel: InfoDetailViewModel by viewModel { parametersOf(getExtraContact()) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityInfoDetailBinding>(this, R.layout.activity_info_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.loadDropDown()

        prepareToolbar()
        btnInfoSave.setOnClickListener { save() }
        observerSave()
        observerTypes()
    }

    private fun observerSave() {
        viewModel.saveSucess().observe(this, Observer {
            setResult(Activity.RESULT_OK)
            toast("Sucesso")
            finish()
        })

        viewModel.saveError().observe(this, Observer {
            setResult(Activity.RESULT_CANCELED)
            toast(it)
        })
    }

    private fun observerTypes() {
        viewModel.dropDown().observe(this, Observer {
            binding.spnInfoType.setItems(it)
            binding.spnInfoType.setOnItemSelectedListener { view, position, id, item ->
                viewModel.contact.type = item as String
            }
        })
    }

    private fun getExtraContact(): Contact {
        return intent.getSerializableExtra(EXTRA_CONTACT) as Contact
    }

    private fun save() {
        viewModel.saveInfo()
    }
}
