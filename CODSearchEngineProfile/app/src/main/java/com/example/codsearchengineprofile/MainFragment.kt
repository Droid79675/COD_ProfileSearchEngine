package com.example.codsearchengineprofile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class MainFragment : Fragment() {

    private lateinit var mProfileViewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recyclerview
        val adapter = RVAdapter()
        {
//            val plantBundle = Bundle()
//            plantBundle.putInt("Key", it.id) // здесь передаем id растения, на карточку которого сейчас переходим  !!!!
//            findNavController().navigate(
//                R.id.action_myPlantsFragment_to_plantInfoFragment,
//                plantBundle
//            )
        }
        val recyclerView = view.rv_profiles
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //adapter.setData(PlantRepository2.plantiklist)

        // UserViewModel
        mProfileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        mProfileViewModel.readAllData.observe(viewLifecycleOwner, Observer { profile ->
            adapter.setData(profile) })

    }

}
