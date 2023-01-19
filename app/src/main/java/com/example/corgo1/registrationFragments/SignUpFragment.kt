package com.example.corgo1.registrationFragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment:Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private var firebaseAuth =  FirebaseAuth.getInstance()
    private val data = FirebaseDatabase.getInstance().getReference("UserInfo")

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

        val controller = Navigation.findNavController(view)

        binding.registration.setOnClickListener{

            val email = binding.registrationEmail.text.toString().trim()
            val password = binding.passwordSignUp.text.toString().trim()
            val repeatPassword = binding.repeatPassword.text.toString().trim()
            val username = binding.usernameSignup.text.toString().trim()


            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("OUR RULES")
                .setMessage("Corgo is created for sharing friendly animal related content, therefore we ask you to upload pictures and posts accordingly! Please follow the rules, spread positivity and refrain from posting negative content, otherwise  your account will be deleted!")
                .setPositiveButton("GOT IT") { dialogInterface, it ->
                    if(email.isNotEmpty() && password.isNotEmpty() && repeatPassword.isNotEmpty() && username.isNotEmpty()){
                        if(password == repeatPassword && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6){
                            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                                if(it.isSuccessful){
                                    data.child(firebaseAuth.currentUser?.uid!!).child("username").setValue(username)
                                    Toast.makeText(requireContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show()
                                    FirebaseAuth.getInstance().signOut()
                                }else{
                                    Toast.makeText(requireContext(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            binding.emailLayout.helperText = "*Enter a valid address"
                        }else if(password.length<6){
                            binding.passLayout.helperText = "*Minimum 6 characters"
                        }else if(password != repeatPassword){
                            binding.repeatPassLayout.helperText = "*Passwords do not match"
                        }
                    }else{
                        Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show()
                    }
                   dialogInterface.dismiss()
                }.show()


        }

        binding.backSignIn.setOnClickListener {
            val action = SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
            controller.navigate(action)
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}