package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.NumberModel
import com.example.myapplication.ui.BaseFragment
import com.example.myapplication.util.getStringText
import com.example.myapplication.util.isInternetAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = HistoryAdapter(arrayListOf())

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (_binding == null) _binding = FragmentHomeBinding.inflate(layoutInflater)
        setupButtons()
        setupRecycler()
        viewModel.getHistory()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupButtons() = with(binding) {
        btnGetFact.setOnClickListener { clickFact(binding.etNumber.getStringText()) }
        btnGetRandomFact.setOnClickListener { clickFact() }
        adapter.onClickHistory = { navigateToDetail(it) }
    }

    private fun setupRecycler() {
        binding.rvHistoryList.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeViewModel.UiState.OnGetFact -> navigateToDetail(state.model)
                is HomeViewModel.UiState.OnGetHistory -> adapter.setItems(state.list)
                is HomeViewModel.UiState.OnError -> snackBarMessage(binding.root, state.error.message)
                else -> return@observe
            }
        }
    }

    private fun clickFact(number: String? = null) {
        val isInternetAvailable = requireContext().isInternetAvailable()
        viewModel.getFact(isInternetAvailable, number)
    }

    private fun navigateToDetail(model: NumberModel) {
        viewModel.saveHistory(model)
        navigateTo(HomeFragmentDirections.toDetail(model))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.uiState.removeObservers(viewLifecycleOwner)
        viewModel.clearState()
        _binding = null
    }
}