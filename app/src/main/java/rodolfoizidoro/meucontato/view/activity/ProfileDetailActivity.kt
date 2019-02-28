package rodolfoizidoro.meucontato.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.ProfileContactsAdapter
import rodolfoizidoro.meucontato.databinding.ActivityProfileDetailBinding
import rodolfoizidoro.meucontato.viewmodel.ProfileDetailViewModel

class ProfileDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_PROFILE_ID = "extra_profile_id"
    }

    private val viewModel by viewModel<ProfileDetailViewModel>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityProfileDetailBinding>(this, R.layout.activity_profile_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        prepareToolbar()
        observerLoad()
        observerSave()
        setupRecyclerView()
        viewModel.loadInfo(getExtraProfileId())
        binding.btnProfileSave.setOnClickListener {
            viewModel.saveProfile()
        }
    }

    private fun getExtraProfileId(): String {
        return intent.getStringExtra(EXTRA_PROFILE_ID)
    }

    private fun observerLoad() {
        viewModel.loadSucess().observe(this, Observer {
            binding.rvProfileContacts.adapter = ProfileContactsAdapter(it.profileContacts)
        })

        viewModel.loadError().observe(this, Observer {
            toast(it.message + "")
        })
    }

    private fun observerSave() {
        viewModel.saveSuccess().observe(this, Observer {
            toast("Salvo com sucesso")
        })

        viewModel.saveError().observe(this, Observer {
            toast(it.localizedMessage)
        })
    }

    private fun setupRecyclerView() {
        binding.rvProfileContacts.layoutManager = LinearLayoutManager(this)
        binding.rvProfileContacts.isNestedScrollingEnabled = false
    }
}
