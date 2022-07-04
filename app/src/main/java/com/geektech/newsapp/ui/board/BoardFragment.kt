package com.geektech.newsapp.ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.FragmentBoardBinding
import com.geektech.newsapp.databinding.FragmentProfileBinding
import com.geektech.newsapp.databinding.ItemNewsBinding


class BoardFragment : Fragment() {
    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter {
            findNavController().navigateUp()

        }
        binding.viewPager.adapter = adapter
        binding.dots.setViewPager2(binding.viewPager)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()

                }

            })

        binding.skip.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}