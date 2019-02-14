package rodolfoizidoro.meucontato.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.MeetupItemBinding
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.util.inflate

class MeetupsAdapter(
    val items: List<MeetupEvent>,
    private val onClick: (MeetupEvent) -> Unit
) : RecyclerView.Adapter<MeetupsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.meetup_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val current = items[position]
            event = current
            executePendingBindings()
            root.setOnClickListener { onClick(current) }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<MeetupItemBinding>(itemView)
    }
}
