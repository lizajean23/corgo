package com.example.corgo1.registrationFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentLoginBinding
import com.example.corgo1.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment:Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

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

        var email = binding.registrationEmail.text.toString()
        var password = binding.passwordSignUp.text.toString()
        var repeatPassword = binding.repeatPassword.text.toString()


        

        binding.registration.setOnClickListener{



            if(email.isEmpty()){
                Toast.makeText(requireContext(), "enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (password.isEmpty()){
                Toast.makeText(requireContext(), "enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if (repeatPassword.isEmpty()){
                Toast.makeText(requireContext(), "repeat password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(password != repeatPassword){
                Toast.makeText(requireContext(), "passwords don't match", Toast.LENGTH_SHORT).show()
            }
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(requireContext(), "წარმატებით შეინახა", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "ERRORR", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}