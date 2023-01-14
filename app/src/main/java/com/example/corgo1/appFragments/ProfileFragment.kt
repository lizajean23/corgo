package com.example.corgo1.appFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.corgo1.MainActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment:Fragment(R.layout.fragment_profile) {

        private var _binding: FragmentProfileBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?


        ): View {


            _binding = FragmentProfileBinding.inflate(inflater,container,false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)




            binding.logOut.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent (view.context, MainActivity::class.java)
                startActivity(intent)

            }


        }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


}