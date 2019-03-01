package rodolfoizidoro.meucontato.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.meetup_fragment.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.MeetupsAdapter
import rodolfoizidoro.meucontato.common.SharedPrefController
import rodolfoizidoro.meucontato.databinding.MeetupFragmentBinding
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.util.setVisibility
import rodolfoizidoro.meucontato.view.activity.FilterCityActivity
import rodolfoizidoro.meucontato.view.activity.MeetupDetailActivity
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel
import java.util.concurrent.TimeUnit


class MeetupsFragment : Fragment() {

    companion object {
        const val TAG = "MeetupsFragment"
        const val REQUEST_CODE_FILTER_CITY = 12
        fun newInstance() = MeetupsFragment()
    }

    private val viewModel: MeetupsViewModel by sharedViewModel()
    private val sharedPrefController: SharedPrefController by inject()
    private var city: City = sharedPrefController.getCity()
    private val searchSubject = BehaviorSubject.create<String>()
    private lateinit var disposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<MeetupFragmentBinding>(inflater, R.layout.meetup_fragment, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMeetupCity.text = city.name
        llSearchCity.setOnClickListener {
            startActivityForResult(Intent(activity, FilterCityActivity::class.java), REQUEST_CODE_FILTER_CITY)
        }
        setupRecyclerView()
        setupSearchView()
        observerCities()
        observerError()
        observerProgress()

        viewModel.find("", city)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                city = data.getSerializableExtra(FilterCityActivity.EXTRA_CITY) as City
                tvMeetupCity.text = city.name
                viewModel.find(svMeetups.query.toString(), city)
            }
        }
    }

    private fun setupSearchView() {
        svMeetups.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.find(query, city)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchSubject.onNext(newText)
                return true
            }
        })
        disposable = searchSubject.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe { viewModel.find(it, city) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun setupRecyclerView() {
        rvMeetups.layoutManager = LinearLayoutManager(context)
    }

    private fun observerCities() {
        viewModel.meetupResponse().observe(this, Observer { list ->
            rvMeetups.adapter = MeetupsAdapter(list) { meetupEvent ->
                context?.startActivity<MeetupDetailActivity>(MeetupDetailActivity.EXTRA_MEETUP to meetupEvent)
            }
        })
    }

    private fun observerProgress() {
        viewModel.progress().observe(this, Observer {
            pbMeetups.setVisibility(it)
        })
    }

    private fun observerError() {
        viewModel.error().observe(this, Observer {
            toast(it)
        })
    }
}
