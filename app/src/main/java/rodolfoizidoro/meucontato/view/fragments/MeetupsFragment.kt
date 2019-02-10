package rodolfoizidoro.meucontato.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel

class MeetupsFragment : Fragment() {

    companion object {
        const val TAG = "MeetupsFragment"
        fun newInstance() = MeetupsFragment()
    }

    private val viewModel: MeetupsViewModel by sharedViewModel<MeetupsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meetups_fragment, container, false)
    }
}
