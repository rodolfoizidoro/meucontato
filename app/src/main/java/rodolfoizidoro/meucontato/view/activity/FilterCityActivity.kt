package rodolfoizidoro.meucontato.view.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_filter_city.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.FilterCityAdapter
import rodolfoizidoro.meucontato.common.SharedPrefController
import rodolfoizidoro.meucontato.databinding.ActivityFilterCityBinding
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.viewmodel.FilterCityViewModel
import java.util.concurrent.TimeUnit


class FilterCityActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CITY = "extra_city"
    }

    private val viewModel by viewModel<FilterCityViewModel>()
    private val sharedPref: SharedPrefController  by inject()
    private val searchSubject = BehaviorSubject.create<String>()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityFilterCityBinding>(this, R.layout.activity_filter_city)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        ivBack.setOnClickListener { finish() }
        observerCities()

        setupRecyclerView()
        setupSearchView()
    }

    private fun observerCities() {
        viewModel.cityResponse().observe(this, Observer { list ->
            binding.rvFilterCity.adapter = FilterCityAdapter(list) { city ->
                saveCity(city)
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvFilterCity.layoutManager = LinearLayoutManager(this)
    }

    private fun setupSearchView() {
        binding.svFilterCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.find(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchSubject.onNext(newText)
                return true
            }
        })

        searchSubject.debounce(500, TimeUnit.MILLISECONDS)
            .filter { it.length > 3 }
            .subscribe { viewModel.find(it) }
    }

    private fun saveCity(city: City) {
        sharedPref.saveCity(city)
        intent.putExtra(EXTRA_CITY, city)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
