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
import com.example.corgo1.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment:Fragment(R.layout.fragment_login) {
    private var _binding:FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
        val email = binding.loginemail.text.toString()
        val password = binding.loginpassword.text.toString()


        binding.LogIn.setOnClickListener { 
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "შეიყვანეთ მონაცემი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val intent = Intent(requireContext(),HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                    }

                }

        }
//        binding.signup.setOnClickListener {
//            val action = LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
//            controller.navigate(action)
//       }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}