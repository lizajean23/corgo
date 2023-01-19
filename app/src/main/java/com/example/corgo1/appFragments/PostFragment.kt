package com.example.corgo1.appFragments
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentPostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class PostFragment:Fragment(R.layout.fragment_post) {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private var uri:Uri? = null
    private var imageUrl : String? = null
    private val auth = FirebaseAuth.getInstance()
    private val dataPosts = FirebaseDatabase.getInstance().getReference("Posts")
    private val dataUser = FirebaseDatabase.getInstance().getReference("UserInfo")
    private var selected : Boolean = false

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

        val activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data
                binding.imageView.setImageURI(uri)
            } else{
                Toast.makeText(requireContext(), "No image Selected", Toast.LENGTH_SHORT).show()
            }

        }


        binding.imageView.setOnClickListener{
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
            selected = true


        }
        binding.upload.setOnClickListener{
            if (selected){
                saveData()
                binding.description.setText("")
            }else{
                Toast.makeText(requireContext(), "Select picture to upload your post", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun saveData(){
        val storageReference = FirebaseStorage.getInstance().reference.child("Images")
            .child(uri!!.lastPathSegment!!)

        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while(!uriTask.isComplete);
            val urlImage = uriTask.result
            imageUrl= urlImage.toString()
            uploadData()
            dialog.dismiss() }
            .addOnFailureListener{
            dialog.dismiss()
        }
    }

    private fun uploadData() {
        val description = binding.description.text.toString()
        dataUser.child(auth.currentUser?.uid!!).get().addOnSuccessListener {

            if (it.exists()) {
                val username = it.child("username").value
                val pfp = it.child("pfp").value
                val id = dataPosts.push().key
                dataPosts.child(id.toString()).child("username").setValue(username)
                dataPosts.child(id.toString()).child("description").setValue(description)
                dataPosts.child(id.toString()).child("image").setValue(imageUrl)
                dataPosts.child(id.toString()).child("pfp").setValue(pfp)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Post uploaded", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}