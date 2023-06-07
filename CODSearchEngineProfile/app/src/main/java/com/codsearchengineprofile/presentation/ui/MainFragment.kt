package com.codsearchengineprofile.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.codsearchengineprofile.App
import com.codsearchengineprofile.R
import com.codsearchengineprofile.databinding.FragmentMainBinding
import com.codsearchengineprofile.presentation.spinner.SpinnerAdapter
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by viewModels {
        factory
    }

    lateinit var intervalWat: String

    override fun onAttach(context: Context) {
        App.appComponent.plusMainComponent()
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)

        val spinner = binding.spinner
        val spinnerAdapter = initSpinnerAdapter()
        spinner.adapter = spinnerAdapter
        initObservers()

        with(binding) {
            inputTextSearchField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mainViewModel.setEvent(
                        Event.OnTextInputEditTextClicked(
                            binding.inputTextSearchField.text.toString(),
                            intervalWat
                        )
                    )
                }
                true
            }
        }
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    intervalWat = parent.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        binding.spinner.onItemSelectedListener = itemSelectedListener
        return binding.root
    }

    private fun initSpinnerAdapter(): SpinnerAdapter {
        val platforms =
            listOf(
                "PSN",
                "XBN",
                "BTL"
            )
        val imagesIds: List<Int> = listOf(
            R.drawable.icon_playstation,
            R.drawable.icon_xbox,
            R.drawable.icon_battlenet
        )
        return SpinnerAdapter(
            requireContext(),
            platforms,
            imagesIds
        )
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {

                when (val state = it.searchViewState) {
                    is SearchViewState.Idle -> {
                        binding.progressBar.isVisible = false
                    }
                    is SearchViewState.Error -> {
                        binding.errorNoInternet.isVisible = true
                    }
                    is SearchViewState.Loading -> {
                        binding.errorNoInternet.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is SearchViewState.Success -> {
                        binding.progressBar.isVisible = false
                        val profile =
                            state.profileDomainModel

                        binding.username.apply {
                            text = "Username: " + profile.username
                            isVisible = true
                        }
                        binding.profileLevel.apply {
                            text = "Profile Level: " + profile.level.toString()
                            isVisible = true
                        }

                        binding.score.apply {
                            text = "Profile Score: " + profile.score.toString()
                            isVisible = true
                        }

                        binding.wins.apply {
                            text = "Wins: " + profile.wins.toString()
                            isVisible = true
                        }

                        binding.losses.apply {
                            text = "Losses: " + profile.losses.toString()
                            isVisible = true
                        }

                        binding.accuracy.apply {
                            text = "Accuracy: " + profile.accuracy.toString()
                            isVisible = true
                        }

                        binding.kdRatio.apply {
                            text = "KD: " + profile.kdRatio.toString()
                            isVisible = true
                        }

                        binding.headshots.apply {
                            text = "Headshots: " + profile.headshots.toString()
                            isVisible = true
                        }

                    }
                }
            }
        }
    }
}

