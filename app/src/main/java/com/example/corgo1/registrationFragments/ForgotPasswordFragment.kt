package com.example.corgo1.registrationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentForgotBinding
import com.example.corgo1.databinding.FragmentLoginBinding
import com.example.corgo1.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment:Fragment(R.layout.fragment_forgot) {

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!

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

        val email = binding.resetEmail.text.toString()
        binding.resetButton.setOnClickListener {

            if(email.isEmpty()){
                Toast.makeText(requireContext(), "შეიყვანეთ მონაცემი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(requireContext(), "We've sent you email to reset password", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
        


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}