package com.example.corgo1.appFragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentPostBinding
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class PostFragment:Fragment(R.layout.fragment_post) {

        private lateinit var imageView: ImageView
        private var _binding: FragmentPostBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = FragmentPostBinding.inflate(inflater,container,false)
            return binding.root



        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            imageView = view.findViewById(R.id.imageView)

            binding.select.setOnClickListener {
                val intent = Intent (Intent.ACTION_PICK)
                intent.type = "image/*"
                galleryActivityResultLauncher.launch(intent)

            }

            binding.upload.setOnClickListener {
                uploadImageToFirebaseStorage()

            }
        }

    var imageUri :Uri? = null
    private val galleryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            if (result.resultCode == Activity.RESULT_OK){
                //image picked
                val data = result.data
                val imageUri = data!!.data
                imageView.setImageURI(imageUri)
            }else{
                Toast.makeText(activity, "cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    private fun uploadImageToFirebaseStorage() {
        if(imageUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
        ref.putFile(imageUri!!)
            .addOnSuccessListener {
                Log.d("upload","uploaded:{it.metadata?.path}")
            }


    }


    override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


}