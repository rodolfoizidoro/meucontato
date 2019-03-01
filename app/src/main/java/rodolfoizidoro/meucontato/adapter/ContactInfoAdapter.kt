package rodolfoizidoro.meucontato.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.ContactInfoItemBinding
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.util.inflate

class ContactInfoAdapter(
    private val items: List<Contact>
) : RecyclerView.Adapter<ContactInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.contact_info_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val current = items[position]
            contact = current
            executePendingBindings()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ContactInfoItemBinding>(itemView)
    }
}
