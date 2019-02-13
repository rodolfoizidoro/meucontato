package rodolfoizidoro.meucontato.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import org.koin.core.Koin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.model.CityResponse

class MeetupsViewModel(val repository: MeetupRepository) : ViewModel() {
//
//    fun searchCity(city : String): Call<CityResponse> {
//       return  repository.findCity("Rio")
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
//
//        })
//    }

}
