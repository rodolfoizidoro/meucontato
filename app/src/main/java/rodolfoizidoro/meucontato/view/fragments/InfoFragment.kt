package rodolfoizidoro.meucontato.view.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.info_fragment.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.InfoAdapter
import rodolfoizidoro.meucontato.view.activity.InfoDetailActivity
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
        setupRecyclerView()
        observerContacts()
        viewModel.loadInfos()

    }

    private fun setupRecyclerView() {
        rvInfo.layoutManager = LinearLayoutManager(context)
    }

    private fun observerContacts() {
        viewModel.contacts().observe(this, Observer { list ->
            rvInfo.adapter = InfoAdapter(list) { contact ->
                startActivityForResult<InfoDetailActivity>(1, InfoDetailActivity.EXTRA_CONTACT to contact)
            }
        })

        viewModel.error().observe(this, Observer {
            toast(it.localizedMessage)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            viewModel.loadInfos()
        }
    }

}
