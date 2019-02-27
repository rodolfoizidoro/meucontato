package rodolfoizidoro.meucontato.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityProfileDetailBinding
import rodolfoizidoro.meucontato.viewmodel.ProfileDetailViewModel

class ProfileDetailActivity : AppCompatActivity() {

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

        viewModel.loadInfo(getExtraProfileId())
        observerLoad()
    }

    private fun getExtraProfileId(): String {
        return intent.getStringExtra(EXTRA_PROFILE_ID)
    }

    private fun observerLoad() {
        viewModel.loadSucess().observe(this, Observer {
        })

        viewModel.loadError().observe(this, Observer {
            toast(it.localizedMessage)
        })
    }
}
