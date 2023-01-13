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
import com.example.corgo1.MainActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment:Fragment(R.layout.fragment_login) {

    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth =  FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = Navigation.findNavController(view)



        binding.LogIn.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful) {
                        val intent = Intent(requireContext(), HomeActivity::class.java)
                        startActivity(intent)
                    } else{
                        Toast.makeText(requireContext(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Fill in all fields", Toast.LENGTH_SHORT).show()
            }



        }


        binding.signup.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            controller.navigate(action)
        }
        binding.forgotPass.setOnClickListener{
            val action = LogInFragmentDirections.actionLogInFragmentToForgotPasswordFragment()
            controller.navigate(action)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}