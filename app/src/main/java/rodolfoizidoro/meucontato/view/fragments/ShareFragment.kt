package rodolfoizidoro.meucontato.view.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.viewmodel.ShareViewModel

class ShareFragment : Fragment() {

    companion object {
        const val TAG = "ShareFragment"
        fun newInstance() = ShareFragment()
    }

    private lateinit var viewModel: ShareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.share_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(ShareViewModel::class.java)
    }

}
