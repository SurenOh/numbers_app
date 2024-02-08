package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.ui.BaseFragment

class DetailFragment : BaseFragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (_binding == null) _binding = FragmentDetailBinding.inflate(layoutInflater)
        setupButtons()
        setupView()
        return binding.root
    }

    private fun setupButtons() {
        binding.btnBack.setOnClickListener { popBackStack() }
    }

    private fun setupView() = with(binding) {
        tvNumber.text = args.model.number
        tvFact.text = args.model.fact
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}