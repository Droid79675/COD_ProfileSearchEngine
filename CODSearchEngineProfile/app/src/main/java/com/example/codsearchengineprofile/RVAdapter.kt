package com.example.codsearchengineprofile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.codsearchengineprofile.model.Profile
import kotlinx.android.synthetic.main.item_profile.view.*

class RVAdapter(private val onItemClick: (Profile) -> Unit) : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {
    private var plantsList = emptyList<Profile>()
    class RVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        return RVViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))

    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentItem = plantsList[position]
        holder.itemView.tv_name.text = currentItem.name
        holder.itemView.profile_item.setOnClickListener(){
            val action = MainFragmentDirections.actionMyPlantsFragmentToPlantInfoFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return plantsList.size
    }
    fun setData(plant: List<Profile>) {
        this.plantsList = plant
        notifyDataSetChanged()
    }
}
