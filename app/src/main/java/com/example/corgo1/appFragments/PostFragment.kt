package com.example.corgo1.appFragments
import android.app.Activity.RESULT_OK
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
import androidx.fragment.app.Fragment
import com.example.corgo1.Posts
import com.example.corgo1.R
import com.example.corgo1.UserImage
import com.example.corgo1.databinding.FragmentPostBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.protobuf.Value
import java.net.URL


class PostFragment:Fragment(R.layout.fragment_post) {

    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
//    private var storageRef = Firebase.storage
    private var uri:Uri? = null
    private var imageUrl : String? = null
    private val auth = FirebaseAuth.getInstance()
    private val dataPosts = FirebaseDatabase.getInstance().getReference("Posts")
    private val dataImages = FirebaseDatabase.getInstance().getReference("userImages")
    private val dataUser = FirebaseDatabase.getInstance().getReference("UserInfo")
    private val tabTitles = arrayListOf("Post", "Feed", "Vaccines", "Profile")

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
//        imageView = view.findViewById(R.id.imageView)
//        storageRef = FirebaseStorage.getInstance()


        val activityResultLauncher = registerForActivityResult<Intent,ActivityResult>(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data
                binding.imageView.setImageURI(uri)
            }else{
                Toast.makeText(requireContext(), "No image Selected", Toast.LENGTH_SHORT).show()
            }

        }


        binding.imageView.setOnClickListener{
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }
        binding.upload.setOnClickListener{
            saveData()

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
            dialog.dismiss()



        }.addOnFailureListener{
            dialog.dismiss()
        }



    }

    private fun uploadData() {
        val imageView = binding.imageView
        val description = binding.description.text.toString()
        dataUser.child(auth.currentUser?.uid!!).get().addOnSuccessListener {

            if (it.exists()) {
                val username = it.child("username").value
                val id = dataPosts.push().key
                dataPosts.child(id.toString()).child("username").setValue(username)
//                val dataClass = Posts(description,imageUrl)
//                FirebaseDatabase.getInstance().getReference("Posts").child(auth.currentUser?.uid!!).setValue(dataClass).addOnCompleteListener {
//                    Toast.makeText(requireContext(), "iqneb qna", Toast.LENGTH_SHORT).show()
//                }
                dataPosts.child(id.toString()).child("description").setValue(description)
                dataPosts.child(id.toString()).child("image").setValue(imageUrl)



                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "AAA QNA", Toast.LENGTH_SHORT).show()
                    }
                val userId = FirebaseAuth.getInstance().currentUser!!.uid


//                            val databaseReference  = FirebaseDatabase.getInstance().getReference("Posts")
//                            databaseReference.child(userId).setValue(username)

//                                .addOnSuccessListener {
//                                    Toast.makeText(activity, "Uploaded picture", Toast.LENGTH_SHORT)
//                                        .show()
//                                }

            }
        }


        }





//es shignit

//        binding.upload.setOnClickListener {

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
//                            dataPosts.child(id.toString()).child("userImages").setValue(imageView)
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

    //            val description = binding.description.text.toString()
    //
    //            dataUser.child(auth.currentUser?.uid!!).get().addOnSuccessListener {
    //
    //                if (it.exists()) {
    //                    val username = it.child("username").value
    //                    val id = dataPosts.push().key
    //                    dataPosts.child(id.toString()).child("username").setValue(username)
    //                    dataPosts.child(id.toString()).child("description").setValue(description)
    //                        .addOnSuccessListener {
    //                            Toast.makeText(requireContext(), "AAA QNA", Toast.LENGTH_SHORT).show()
    //                        }
    //
    //                }
    //            }
    //
    //        }
    //




//    private val galleryImage = registerForActivityResult(
//        ActivityResultContracts.GetContent(),
//        ActivityResultCallback{
//            imageView.setImageURI(it)
//            if (it != null) {
//                uri = it
//            }
//
//        }
//    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}