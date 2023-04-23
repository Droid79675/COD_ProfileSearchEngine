package com.codsearchengineprofile.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.codsearchengineprofile.databinding.FragmentMainBinding
import com.codsearchengineprofile.domain.model.ProfileDomainModel
import com.codsearchengineprofile.presentation.ui.contract.Event
import com.codsearchengineprofile.presentation.ui.contract.SearchViewState

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var intervalWat: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentMainBinding.inflate(layoutInflater)

        initObservers()
        binding.searchField.setOnClickListener {
            mainViewModel.setEvent(
                Event.OnTextInputEditTextClicked(
                    binding.inputTextSearchField.text.toString(),
                    binding.spinner.onItemSelectedListener.toString()
                )
            )
        }
        binding.spinner.setOnClickListener {
            mainViewModel.setEvent(Event.OnSpinnerClicked)
        }

    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.uiState.collect {
                when (it.searchViewState) {
                    is SearchViewState.Idle -> {
                        binding.progressBar.isVisible = false

                        val intervals =
                            arrayOf("PSN", "XBN", "BTL")
                        val spinner = binding.spinner
                        val adapter: ArrayAdapter<String?> =
                            ArrayAdapter<String?>(
                                requireContext(),
                                android.R.layout.simple_spinner_item,
                                intervals
                            )
                        spinner.adapter = adapter
                        val itemSelectedListener: AdapterView.OnItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    intervalWat =
                                        parent.getItemAtPosition(position) as String //нужен чтобы достать инфу какую платформу юзер выбрал
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {}

                            }
                        spinner.onItemSelectedListener = itemSelectedListener
                    }
                    is SearchViewState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SearchViewState.Success -> {
                        binding.progressBar.isVisible = false
                        val profile: ProfileDomainModel =
                            it.searchViewState.profileDomainModel

                        binding.username.run {
                            text = profile.username
                            isVisible = true
                        }
                        binding.profileLevel.run {
                            text = profile.level.toString()
                            isVisible = true
                        }

                        binding.score.run {
                            text = profile.score.toString()
                            isVisible = true
                        }

                        binding.wins.run {
                            text = profile.wins.toString()
                            isVisible = true
                        }

                        binding.losses.run {
                            text = profile.losses.toString()
                            isVisible = true
                        }

                        binding.accuracy.run {
                            text = profile.accuracy.toString()
                            isVisible = true
                        }

                        binding.kdRatio.run {
                            text = profile.kdRatio.toString()
                            isVisible = true
                        }

                        binding.headshots.run {
                            text = profile.headshots.toString()
                            isVisible = true
                        }

                    }
                }
            }
        }
    }
}
