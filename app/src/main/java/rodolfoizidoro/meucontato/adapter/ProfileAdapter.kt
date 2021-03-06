package rodolfoizidoro.meucontato.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.InfoItemBinding
import rodolfoizidoro.meucontato.databinding.MeetupItemBinding
import rodolfoizidoro.meucontato.databinding.ProfileItemBinding
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.model.core.Contact
import rodolfoizidoro.meucontato.model.core.Profile
import rodolfoizidoro.meucontato.util.inflate

class ProfileAdapter(
    private val items: List<Profile>,
    private val onClick: (Profile) -> Unit): RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.profile_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val current = items[position]
            profile = current
            executePendingBindings()
            root.setOnClickListener { onClick(current) }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ProfileItemBinding>(itemView)
    }
}
