package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ActivityMeetupDetailBinding
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.viewmodel.MeetupDetailViewModel

class MeetupDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MEETUP = "extra_meetup"
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMeetupDetailBinding>(this, R.layout.activity_meetup_detail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setLifecycleOwner(this)
        binding.viewModel = MeetupDetailViewModel(getMeetup())
    }

    private fun getMeetup() = intent.extras.getSerializable(EXTRA_MEETUP) as MeetupEvent
}
