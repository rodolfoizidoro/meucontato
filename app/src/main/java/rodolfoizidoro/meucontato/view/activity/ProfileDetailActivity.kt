package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.viewmodel.ProfileDetailViewModel

class ProfileDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PROFILE = "extra_profile"
    }

    private val viewModel by viewModel<ProfileDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        viewModel.loadInfo("91ekUVioyC0TPIP3XAb9")
        observerSave()
    }

    private fun getExtraProfile(): Profile {
        return intent.getSerializableExtra(EXTRA_PROFILE) as Profile
    }

    private fun observerSave() {
        viewModel.loadSucess().observe(this, Observer {
            val a = it
        })

        viewModel.loadError().observe(this, Observer {
            toast(it.localizedMessage)
        })
    }
}
