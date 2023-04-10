package com.codsearchengineprofile.presentation.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.R
import io.reactivex.Single
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfilesAdapter(private val onItemClick: (ProfileDomainModel) -> Unit) : RecyclerView.Adapter<ProfilesAdapter.ProfilesViewHolder>() {
    private var profilesList: List<ProfileDomainModel> = emptyList()

    class ProfilesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesViewHolder {
        return ProfilesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_profile, parent, false))

    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        val currentItem = profilesList[position]
        holder.itemView.tv_name.text = currentItem.username
//        holder.itemView.item_profile.setOnClickListener(){
//            val action = MainFragmentDirections.actionMyPlantsFragmentToPlantInfoFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }
    fun setData(profile: List<ProfileDomainModel>) {
        this.profilesList = profile
        notifyDataSetChanged()
    }
}
