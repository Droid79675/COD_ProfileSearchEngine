package com.codsearchengineprofile.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codsearchengineprofile.R
import com.codsearchengineprofile.databinding.FragmentMainBinding
import com.codsearchengineprofile.domain.models.ProfileDomainModel
import com.codsearchengineprofile.domain.usecases.GetProfileModelUseCase

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val api: GetProfileModelUseCase = GetProfileModelUseCase()

    lateinit var intervalWat: String

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
                    is MainContract.SearchViewState.Idle -> {
                        binding.progressBar.isVisible = false

                        val intervals =
                            arrayOf("PSN", "XBN", "BTL")
                        val spinner = binding.spinner
                        val adapter: ArrayAdapter<String?> =
                            ArrayAdapter<String?>(
                                requireContext(),
                                R.layout.simple_spinner_item,
                                intervals
                            )
                        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
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
                                        parent.getItemAtPosition(position) as String 
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {}

                            }
                        spinner.onItemSelectedListener = itemSelectedListener
                    }
                    is MainContract.SearchViewState.Loading -> {
                        val username = binding.filledEditText.text.toString()
                        val platform = intervalWat
                        binding.progressBar.isVisible = true
                        val profile: ProfileDomainModel =
                            it.searchViewState.getProfileModelUseCase.getProfile(
                                username,
                                platform
                            )
                    }
                    is MainContract.SearchViewState.Success -> {

                        val adapter = ProfilesAdapter {

                        }
                        val recyclerView: RecyclerView = binding.rvProfiles
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        adapter.setData()

                    }
                }
            }
        }
    }
}
