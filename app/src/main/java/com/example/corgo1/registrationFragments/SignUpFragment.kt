package com.example.corgo1.registrationFragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.corgo1.HomeActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment:Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth =  FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.registration.setOnClickListener{

            val email = binding.registrationEmail.text.toString()
            val password = binding.passwordSignUp.text.toString()
            val repeatPassword = binding.repeatPassword.text.toString()
            val username = binding.usernameSignup.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty() && username.isNotEmpty()){
                if(password == repeatPassword && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(requireContext(), HomeActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.emailLayout.helperText = "*Enter a valid adress"
                }else if(password.length<6){
                    binding.passLayout.helperText = "*Minimum 6 characters"
                }else if(password != repeatPassword){
                   binding.repeatPassLayout.helperText = "*Passwords do not match"
                }
            }else{
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}