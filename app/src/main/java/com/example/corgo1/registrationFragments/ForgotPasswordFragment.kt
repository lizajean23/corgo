package com.example.corgo1.registrationFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.corgo1.HomeActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentForgotBinding

import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment:Fragment(R.layout.fragment_forgot) {

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth =  FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForgotBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = Navigation.findNavController(view)

        binding.resetButton.setOnClickListener {

            val email = binding.resetEmail.text.toString()

            if(email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                    if(it.isSuccessful) {
                        Toast.makeText(requireContext(),"Email Sent", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(requireContext(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Enter email", Toast.LENGTH_SHORT).show()
            }

        }

        binding.backSignin.setOnClickListener {
            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLogInFragment()
            controller.navigate(action)
        }
        


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}