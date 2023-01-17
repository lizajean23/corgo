package com.example.corgo1.appFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.corgo1.MainActivity
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class ProfileFragment:Fragment(R.layout.fragment_profile) {

        private var _binding: FragmentProfileBinding? = null
        private val binding get() = _binding!!
        private lateinit var pfp: ImageView
        private val dataImages = FirebaseDatabase.getInstance().getReference("userImage")
        private val dataPosts = FirebaseDatabase.getInstance().getReference("Posts")
        private val dataUser = FirebaseDatabase.getInstance().getReference("UserInfo")
        private val auth = FirebaseAuth.getInstance()
        private lateinit var uri:Uri
        private var storageRef = Firebase.storage


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
            pfp = binding.profilePic
            storageRef = FirebaseStorage.getInstance()




            binding.logOut.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent (view.context, MainActivity::class.java)
                startActivity(intent)

            }
//
//            binding.profilePic.setOnClickListener{
//                galleryImage.launch("image/*")
//            }
//
//            binding.uploadPfp.setOnClickListener {
//            storageRef.getReference("images").child(System.currentTimeMillis().toString())
//                .putFile(uri)
//                .addOnSuccessListener {
//                        task ->
//                    task.metadata!!.reference!!.downloadUrl
//                        .addOnSuccessListener{
//                            val userId = FirebaseAuth.getInstance().currentUser!!.uid
//                            val mapImage = mapOf(
//                                "url" to it.toString()
//                            )
//
//                            dataPosts.child(id.toString()).child("userImages").setValue(pfp)
//
//                            val databaseReference  = FirebaseDatabase.getInstance().getReference("userImages")
//                            databaseReference.child(userId).setValue(mapImage)
//
//                                .addOnSuccessListener {
//                                    Toast.makeText(activity, "Uploaded picture", Toast.LENGTH_SHORT)
//                                        .show()
//                                }
//                                .addOnFailureListener {
//                                    Toast.makeText(activity, "could not up", Toast.LENGTH_SHORT).show()
//                                }
//                        }
//                }
//            }
//
//            dataUser.child(auth.currentUser?.uid!!).addValueEventListener(object:
//                ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (!snapshot.hasChild("username")) {
//
//                    }
//                    val userInfo = snapshot.getValue(com.example.corgo1.UserInfo::class.java) ?: return
//                     binding.username.text = userInfo.username
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                }
//
//            })
//
//
//        }
//        private val galleryImage = registerForActivityResult(
//        ActivityResultContracts.GetContent(),
//        ActivityResultCallback{
//            pfp.setImageURI(it)
//            if (it != null) {
//                uri = it
//            }
//
        }
//    )

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }


}