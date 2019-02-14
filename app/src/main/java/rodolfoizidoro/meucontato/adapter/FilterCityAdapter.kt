package rodolfoizidoro.meucontato.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.databinding.FilterCityItemBinding
import rodolfoizidoro.meucontato.model.City
import rodolfoizidoro.meucontato.model.MeetupEvent
import rodolfoizidoro.meucontato.util.inflate
import java.util.function.Consumer

class FilterCityAdapter(
    val cities: List<City>,
    private val onClick: (City) -> Unit
) : RecyclerView.Adapter<FilterCityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.filter_city_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.run {
            val currentCity = cities[position]
            city = currentCity
            executePendingBindings()
            root.setOnClickListener { onClick(currentCity) }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<FilterCityItemBinding>(itemView)
    }
}
