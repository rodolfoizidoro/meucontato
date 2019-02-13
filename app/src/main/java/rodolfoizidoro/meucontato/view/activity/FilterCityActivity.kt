package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_filter_city.*
import org.koin.androidx.viewmodel.ext.android.viewModel

import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityFilterCityBinding
import rodolfoizidoro.meucontato.viewmodel.FilterCityViewModel

class FilterCityActivity : AppCompatActivity() {

    private val viewModel by viewModel<FilterCityViewModel>()


    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityFilterCityBinding>(this, R.layout.activity_filter_city)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        ivBack.setOnClickListener { finish() }
        observerCitityes()
    }

    override fun onResume() {
        super.onResume()
        val b = viewModel.find("100")
        val c = ""
    }

    fun observerCitityes(){
        viewModel.cityResponse().observe(this, Observer {
            Log.e("LOAD_CITY", "$it")

            val b = it
            val c = ""
        })
    }
}
