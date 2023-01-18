package com.example.corgo1.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.corgo1.databinding.ChangepassDialogBinding
import com.google.firebase.auth.FirebaseAuth


class ChangePassDialog: DialogFragment() {


    private var _binding: ChangepassDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ChangepassDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changebutton.setOnClickListener {
            val pass1 = binding.pass1text.text.toString()
            val pass2 = binding.pass2text.text.toString()

            if (pass1.length >= 6 && pass1 == pass2){
                FirebaseAuth.getInstance().currentUser?.updatePassword(pass2)?.addOnCompleteListener{
                  if(it.isSuccessful){
                      Toast.makeText(requireContext(), "Password changed", Toast.LENGTH_SHORT).show()
                      dismiss()
                  }else{
                      Toast.makeText(requireContext(), it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                  }
                }
            }else if(pass1.length < 6){
                binding.pass1.helperText= "Password should be minimum 6 characters"
            }else if(pass2 != pass1){
                binding.pass2.helperText = "Passwords do not match"
            }else if(pass1.isEmpty() || pass2.isEmpty()){
                Toast.makeText(requireContext(), "Fill in both fields", Toast.LENGTH_SHORT).show()
            }

        }

    }



}


