package com.example.corgo1.appFragments
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
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentPostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class PostFragment:Fragment(R.layout.fragment_post) {

    private lateinit var imageView: ImageView
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private var storageRef = Firebase.storage
    private lateinit var uri:Uri
    private val auth = FirebaseAuth.getInstance()
    private val dataPosts = FirebaseDatabase.getInstance().getReference("Posts")
    private val dataUser = FirebaseDatabase.getInstance().getReference("UserInfo")

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
        storageRef = FirebaseStorage.getInstance()

        binding.select.setOnClickListener {
            galleryImage.launch("image/*")
        }

        binding.upload.setOnClickListener {

            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener {
                        task ->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener{
                            val userId = FirebaseAuth.getInstance().currentUser!!.uid
                            val mapImage = mapOf(
                                "url" to it.toString()
                            )
                            val databaseReference   = FirebaseDatabase.getInstance().getReference("userImages")
                            databaseReference.child(userId).setValue(mapImage)

                                .addOnSuccessListener {
                                    Toast.makeText(activity, "Uploaded picture", Toast.LENGTH_SHORT)
                                        .show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(activity, "could not up", Toast.LENGTH_SHORT).show()
                                }
                        }
                }

            val description = binding.description.text.toString()
            dataUser.child(auth.currentUser?.uid!!).get().addOnSuccessListener {
                if (it.exists()) {
                    val userName = it.child("fullName").value
                    val image = it.child("userImages").value

                    dataPosts.child(id.toString()).child("userName").setValue(userName)
                    dataPosts.child(id.toString()).child("postDescription").setValue(description)
                    dataPosts.child(id.toString()).child("userImages").setValue(image)
                }
            }

        }

    }


    private val galleryImage = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ActivityResultCallback{
            imageView.setImageURI(it)
            if (it != null) {
                uri = it
            }

        }
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}