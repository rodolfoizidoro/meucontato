package rodolfoizidoro.meucontato.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.profiles_fragment.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.ProfileAdapter
import rodolfoizidoro.meucontato.view.activity.ProfileDetailActivity
import rodolfoizidoro.meucontato.viewmodel.ProfilesViewModel

class ProfilesFragment : Fragment() {

    companion object {
        const val TAG = "ProfilesFragment"
        fun newInstance() = ProfilesFragment()
    }

    private val viewModel: ProfilesViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profiles_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerContacts()
        viewModel.loadProfiles()
    }

    private fun setupRecyclerView() {
        rvProfiles.layoutManager = LinearLayoutManager(context)
    }

    private fun observerContacts() {
        viewModel.profiles().observe(this, Observer { list ->
            rvProfiles.adapter = ProfileAdapter(list) { profile ->
                startActivityForResult<ProfileDetailActivity>(1, ProfileDetailActivity.EXTRA_PROFILE_ID to profile.id)
            }
        })

        viewModel.error().observe(this, Observer {
            toast(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            viewModel.loadProfiles()
        }
    }
}
