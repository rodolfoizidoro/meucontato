package rodolfoizidoro.meucontato.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.viewmodel.InfoViewModel

class InfoFragment : Fragment() {

    companion object {
        const val TAG = "InfoFragment"
        fun newInstance() = InfoFragment()
    }

    private val viewModel: InfoViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerContacts()
        viewModel.loadInfos()

    }

    private fun observerContacts() {
        viewModel.contacts().observe(this, Observer {
            val a = it

        })

        viewModel.error().observe(this, Observer {
            val b = it
        })
    }

}
