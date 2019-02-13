package rodolfoizidoro.meucontato.view.fragments

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
import rodolfoizidoro.meucontato.model.CityResponse
import rodolfoizidoro.meucontato.view.activity.FilterCityActivity
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel

class MeetupsFragment : Fragment() {

    companion object {
        const val TAG = "MeetupsFragment"
        fun newInstance() = MeetupsFragment()
    }

    private val viewModel: MeetupsViewModel by sharedViewModel<MeetupsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.meetups_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("RODOLFOMEETUP", "")
        llSearchCity.setOnClickListener {
            startActivityForResult(Intent(activity, FilterCityActivity::class.java), 0)
        }
//        val a = viewModel.searchCity("sao paulo")
//        a.enqueue(object : Callback<CityResponse> {
//            override fun onFailure(call: Call<CityResponse>, t: Throwable)
//             {
//                 val b = call
//              Log.d("RODOLFOMEETUP", "")
//            }
//
//            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
//                val b = response.body()
//                Log.d("RODOLFOMEETUP", "")
//            }
//        })
    }
}
