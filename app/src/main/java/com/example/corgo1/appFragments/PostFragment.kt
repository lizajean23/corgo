package com.example.corgo1.appFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentPostBinding

class PostFragment:Fragment(R.layout.fragment_post) {
    class FeedFragment:Fragment(R.layout.fragment_feed) {

        private var _binding: FragmentPostBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = FragmentPostBinding.inflate(inflater,container,false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }

    }
}