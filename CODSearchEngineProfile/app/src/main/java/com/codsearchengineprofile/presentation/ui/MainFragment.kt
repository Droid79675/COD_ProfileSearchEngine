package com.codsearchengineprofile.presentation.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codsearchengineprofile.databinding.FragmentMainBinding
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    private val api: GetProfileModelUseCase = GetProfileModelUseCase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)

        initObservers()
        binding.tilFragmentMain.setOnClickListener {
            mainViewModel.setEvent(MainContract.Event.OnTextInputEditTextClicked)
        }
        binding.spinner.setOnClickListener {
            mainViewModel.setEvent(MainContract.Event.OnSpinnerClicked)
        }
//        binding.secondActivity.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
//        }

    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {
                when (it.searchViewState) {
                    is MainContract.SearchViewState.Idle -> { binding.progressBar.isVisible = false }
                    is MainContract.SearchViewState.Loading -> { binding.progressBar.isVisible = true }
                    is MainContract.SearchViewState.Success -> {

                        val adapter: ProfilesAdapter = ProfilesAdapter {

                        }
                        val recyclerView: RecyclerView = binding.rvProfiles
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        convertSingleIntoList(it.searchViewState.getProfileModelUseCase.execute())
                        adapter.setData()
                    }
                }
            }
        }

    }

    private fun convertSingleIntoList(getProfileModelUseCase: Single<ProfileDomainModel>){
        var profileDomainList: List<ProfileDomainModel> = emptyList()
        for(single in getProfileModelUseCase){

        }
    }

}
