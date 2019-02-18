package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_meetup_detail.*
import org.jetbrains.anko.browse
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityMeetupDetailBinding
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.util.MapUtil
import rodolfoizidoro.meucontato.viewmodel.MeetupDetailViewModel

class MeetupDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MEETUP = "extra_meetup"
    }

    private val meetupMap: SupportMapFragment by lazy {
        fgMap as SupportMapFragment
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMeetupDetailBinding>(this, R.layout.activity_meetup_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setLifecycleOwner(this)
        binding.viewModel = MeetupDetailViewModel(getMeetup())
        binding.ivBack.setOnClickListener { finish() }

        meetupMap.getMapAsync { onMapReaded(it) }
        btMoreInfo.setOnClickListener {
            browse(getMeetup().eventUrl)
        }
    }

    private fun onMapReaded(googleMap: GoogleMap) {
        if (getMeetup().hasLocationMap()) {
           val mapUtil = MapUtil(baseContext, googleMap)
            mapUtil.setupMap(false)
            mapUtil.setLocation(getMeetup().locationCoord(), getMeetup().name)
        } else {
            fgMap.view?.visibility = View.GONE
        }
    }



    private fun getMeetup() = intent.extras.getSerializable(EXTRA_MEETUP) as MeetupEvent
}
