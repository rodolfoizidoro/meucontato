package rodolfoizidoro.meucontato.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.InfoItemBinding
import rodolfoizidoro.meucontato.databinding.MeetupItemBinding
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.util.inflate

class InfoAdapter(
    val items: List<Contact>,
    private val onClick: (Contact) -> Unit
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.info_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val current = items[position]
            contact = current
            executePendingBindings()
            root.setOnClickListener { onClick(current) }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<InfoItemBinding>(itemView)
    }
}
