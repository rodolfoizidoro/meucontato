package rodolfoizidoro.meucontato.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.meetup_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.MeetupsAdapter
import rodolfoizidoro.meucontato.common.SharedPrefController
import rodolfoizidoro.meucontato.databinding.MeetupFragmentBinding
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.view.activity.FilterCityActivity
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView


class MeetupsFragment : Fragment() {

    companion object {
        const val TAG = "MeetupsFragment"
        const val REQUEST_CODE_FILTER_CITY = 12
        fun newInstance() = MeetupsFragment()
    }

    private val viewModel: MeetupsViewModel by sharedViewModel()
    private val sharedPrefController: SharedPrefController by inject()
    private var city: City = sharedPrefController.getCity()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<MeetupFragmentBinding>(inflater, R.layout.meetup_fragment, container, false)
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
        observerCities()

        viewModel.find("mobile", city)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                city = data.getSerializableExtra(FilterCityActivity.EXTRA_CITY) as City
                tvMeetupCity.text = city.name
            }
        }
    }

    private fun setupRecyclerView() {
        rvMeetups.layoutManager = LinearLayoutManager(context)
    }

    private fun observerCities() {
        viewModel.meetupResponse().observe(this, Observer { list ->
            rvMeetups.adapter = MeetupsAdapter(list) { }
        })
    }
}
