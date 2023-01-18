package com.example.corgo1.appFragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.corgo1.MainActivity
import com.example.corgo1.R
import com.example.corgo1.components.ChangePassDialog
import com.example.corgo1.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.checkerframework.checker.units.qual.A

class ProfileFragment:Fragment(R.layout.fragment_profile) {

        private var _binding: FragmentProfileBinding? = null
        private val binding get() = _binding!!
        private val auth = FirebaseAuth.getInstance()
        private val dataUser = FirebaseDatabase.getInstance().getReference("UserInfo")
        private var uri:Uri? = null
        private var imageUrl : String? = null
        private lateinit var builder: AlertDialog.Builder


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?


        ): View {


            _binding = FragmentProfileBinding.inflate(inflater,container,false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.logOut.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("LOG OUT")
                    .setMessage("Are you sure you want to log out?")
                    .setCancelable(true)
                    .setPositiveButton("Yes"){ dialogInterface, it ->
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent (view.context, MainActivity::class.java)
                        startActivity(intent)
                    }
                    .setNegativeButton("Cancel"){ dialogInterface, it ->
                        dialogInterface.cancel()
                    }
                    .show()


            }

            binding.changepass.setOnClickListener {
                val showDialog = ChangePassDialog()
                showDialog.show((activity as AppCompatActivity).supportFragmentManager, "showDialog")
            }

            val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
                ActivityResultContracts.StartActivityForResult()){ result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val data = result.data
                    uri = data!!.data
                    binding.profilePic.setImageURI(uri)
                }else{
                    Toast.makeText(requireContext(), "No image Selected", Toast.LENGTH_SHORT).show()
                }

            }

            binding.profilePic.setOnClickListener{
                val photoPicker = Intent(Intent.ACTION_PICK)
                photoPicker.type = "image/*"
                activityResultLauncher.launch(photoPicker)
            }
            binding.uploadPfp.setOnClickListener{
                saveData()

            }

            dataUser.child(auth.currentUser?.uid!!).addValueEventListener(object:
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val userInfo = snapshot.getValue(com.example.corgo1.UserInfo::class.java) ?: return
                    binding.username.text = userInfo.username
                    val profilePic = binding.profilePic
                    Glide.with(this@ProfileFragment).load(userInfo.pfp).into(binding.profilePic)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


        }

    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("pfpImages")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageUrl = urlImage.toString()
            uploadData()
            dialog.dismiss()

        }.addOnFailureListener{
            dialog.dismiss()
        }


    }

    private fun uploadData() {
        dataUser.child(auth.currentUser?.uid!!).get().addOnSuccessListener {

            if(it.exists()){

                dataUser.child(auth.currentUser?.uid!!.toString()).child("pfp").setValue(imageUrl)

                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "You uploaded your profile picture", Toast.LENGTH_SHORT).show()
                    }
            }

        }
    }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


}