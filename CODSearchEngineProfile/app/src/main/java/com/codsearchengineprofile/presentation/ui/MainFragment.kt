package com.codsearchengineprofile.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codsearchengineprofile.databinding.FragmentMainBinding
import com.codsearchengineprofile.presentation.spinner.SpinnerAdapter
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState
import com.codsearchengineprofile.presentation.ui.contract.SpinnerViewState

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var intervalWat: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)
        val spinnerAdapter = initSpinnerAdapter()
        initObservers(spinnerAdapter)

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    intervalWat = parent.getItemAtPosition(position) as String
                    mainViewModel.setEvent(
                        Event.OnSpinnerChose(
                            intervalWat
                        )
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        binding.spinner.onItemSelectedListener = itemSelectedListener
        binding.searchField.setOnClickListener {
            mainViewModel.setEvent(
                Event.OnTextInputEditTextClicked(
                    binding.inputTextSearchField.text.toString(),
                    intervalWat
                )
            )
        }
        binding.spinner.setOnClickListener {
            mainViewModel.setEvent(
                Event.OnSpinnerClicked
            )
        }
    }

    private fun initSpinnerAdapter(): SpinnerAdapter {
        val platforms: MutableList<String> = mutableListOf()
        val imagesIds: MutableList<Int> = mutableListOf()
        return SpinnerAdapter(
            requireContext(),
            platforms,
            imagesIds
        )
    }

    private fun initObservers(spinnerAdapter: SpinnerAdapter) {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {
                when (val state = it.spinnerViewState) {
                    is SpinnerViewState.Idle -> {
                    }

                    is SpinnerViewState.Opened -> {
                        spinnerAdapter.platforms = state.platforms
                        spinnerAdapter.imagesIds = state.imagesIds
                    }

                    is SpinnerViewState.ChangedImage -> {
                        when (state.platform) {
                            "PSN" -> binding.spinner.setPopupBackgroundResource(spinnerAdapter.imagesIds[0])
                            "XBN" -> binding.spinner.setPopupBackgroundResource(spinnerAdapter.imagesIds[1])
                            "BTL" -> binding.spinner.setPopupBackgroundResource(spinnerAdapter.imagesIds[2])
                        }
                    }
                }

                val spinner = binding.spinner
                spinner.adapter = spinnerAdapter

                when (val state = it.searchViewState) {
                    is SearchViewState.Idle -> {
                        binding.progressBar.isVisible = false
                    }
                    is SearchViewState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SearchViewState.Success -> {
                        binding.progressBar.isVisible = false
                        val profile =
                            state.profileDomainModel

                        binding.username.apply {
                            text = profile.username
                            isVisible = true
                        }
                        binding.profileLevel.apply {
                            text = profile.level.toString()
                            isVisible = true
                        }

                        binding.score.apply {
                            text = profile.score.toString()
                            isVisible = true
                        }

                        binding.wins.apply {
                            text = profile.wins.toString()
                            isVisible = true
                        }

                        binding.losses.apply {
                            text = profile.losses.toString()
                            isVisible = true
                        }

                        binding.accuracy.apply {
                            text = profile.accuracy.toString()
                            isVisible = true
                        }

                        binding.kdRatio.apply {
                            text = profile.kdRatio.toString()
                            isVisible = true
                        }

                        binding.headshots.apply {
                            text = profile.headshots.toString()
                            isVisible = true
                        }

                    }
                }
            }
        }
    }
}
