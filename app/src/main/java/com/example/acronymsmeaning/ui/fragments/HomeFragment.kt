package com.example.acronymsmeaning.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.acronymsmeaning.R
import com.example.acronymsmeaning.data.base.LiveDataResource
import com.example.acronymsmeaning.data.remote.models.LfsItem
import com.example.acronymsmeaning.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment: DaggerFragment() {

    val TAG = "HomeFragment"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeFragmentViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var acronymResults: MutableList<LfsItem>
    private lateinit var acronymResultsAdapter: AcronymResultsAdapter

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewmodel = homeFragmentViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setObservers()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("customOnClickListener")
        fun View.customOnClickListener(viewModel: HomeViewModel) {
            setOnClickListener {
                viewModel.getAcronymResults()
                ViewCompat.getWindowInsetsController(it)
                    ?.hide(WindowInsetsCompat.Type.ime())
            }
        }
    }

    private fun setupAdapter() {
        acronymResults = mutableListOf()
        acronymResultsAdapter = AcronymResultsAdapter(acronymResults)
        binding.rvAcronymList.apply {
            adapter = acronymResultsAdapter
        }
    }

    private fun setObservers() {
        homeFragmentViewModel.weatherData.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    LiveDataResource.Status.LOADING -> {
                        Log.d(TAG, "loading..")
                    }
                    LiveDataResource.Status.SUCCESS -> {
                        it.data?.let { it1 ->
                            it1[0]?.lfs?.let { it2 ->
                                acronymResults.clear()
                                acronymResults.addAll(it2)
                            }
                            acronymResultsAdapter.notifyDataSetChanged()
                        }
                    }
                    else -> {
                        Log.d(TAG, "an error happened")
                    }
                }
            }
        }
    }
}