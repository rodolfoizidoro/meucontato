package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.viewmodel.InfoDetailViewModel

class InfoDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
    }

    val viewModel: InfoDetailViewModel by viewModel { parametersOf(getExtraContact()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_detail)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbarInfoDetail)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun getExtraContact(): Contact? {
        return intent.getSerializableExtra(EXTRA_CONTACT) as Contact?
    }
}
