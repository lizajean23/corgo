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
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment:Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentLoginBinding? = null
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

        val email = binding.registrationEmail.text.toString()
        val password = binding.passwordSignUp.text.toString()
        val repeatPassword = binding.repeatPassword.text.toString()

        binding.registration.setOnClickListener{
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "შეიყვანეთ მონაცემი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
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