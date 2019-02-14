package rodolfoizidoro.meucontato.view.fragments

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.meetups_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.common.Navigator
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.CityResponse
import rodolfoizidoro.meucontato.view.activity.FilterCityActivity
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel

class MeetupsFragment : Fragment() {

    companion object {
        const val TAG = "MeetupsFragment"
        const val REQUEST_CODE_FILTER_CITY = 12
        fun newInstance() = MeetupsFragment()
    }

    private val viewModel: MeetupsViewModel by sharedViewModel<MeetupsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meetups_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llSearchCity.setOnClickListener {
            startActivityForResult(Intent(activity, FilterCityActivity::class.java), REQUEST_CODE_FILTER_CITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val a = data?.let {
                data.getSerializableExtra(FilterCityActivity.EXTRA_CITY) as City
            }
        }
    }
}
