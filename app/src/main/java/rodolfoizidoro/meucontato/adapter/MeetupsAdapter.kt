package rodolfoizidoro.meucontato.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.util.inflate
import java.util.function.Consumer

class MeetupsAdapter(
    val context: Context,
    val items: List<MeetupEvent>,
    val action: Consumer<MeetupEvent>
) : RecyclerView.Adapter<MeetupsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.meetup_item)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], action)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: MeetupEvent, listener: Consumer<MeetupEvent>) = with(itemView) {

        }
    }
}
