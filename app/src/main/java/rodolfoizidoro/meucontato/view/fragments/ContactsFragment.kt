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
import kotlinx.android.synthetic.main.contacts_fragment.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.adapter.ContactsAdapter
import rodolfoizidoro.meucontato.view.activity.ContactInfoActivity
import rodolfoizidoro.meucontato.viewmodel.ContactsViewModel

class ContactsFragment : Fragment() {

    companion object {
        const val TAG = "ContactsFragment"
        fun newInstance() = ContactsFragment()
    }

    private val viewModel by sharedViewModel<ContactsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contacts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerContacts()
        viewModel.loadContacts()
    }

    private fun setupRecyclerView() {
        rvContacts.layoutManager = LinearLayoutManager(context)
    }

    private fun observerContacts() {
        viewModel.contacts().observe(this, Observer { list ->
            rvContacts.adapter = ContactsAdapter(list) { profile ->
                startActivityForResult<ContactInfoActivity>(1, ContactInfoActivity.EXTRA_CONTACT_INFO to profile)
            }
        })

        viewModel.error().observe(this, Observer {
            toast(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            viewModel.loadContacts()
        }
    }

}
