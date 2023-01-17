package com.example.corgo1.appFragments

import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corgo1.Posts
import com.example.corgo1.R
import com.example.corgo1.UserImage
import com.example.corgo1.adapter.RecyclerViewPostAdapter
import com.example.corgo1.databinding.FragmentFeedBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FeedFragment:Fragment(R.layout.fragment_feed  ) {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
//    private lateinit var images :ArrayList<UserImage>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {

        _binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewPost.layoutManager = LinearLayoutManager(requireContext())
//        images = arrayListOf()
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                var list = ArrayList<Posts>()
//                for(data in dataSnapshot.children){
//                    var post  = data.getValue(Posts::class.java)
//                    list.add(post as Posts )
//                }
//                if (list.size>0){
//                    val adapter = RecyclerViewPostAdapter(list)
//                    binding.recyclerViewPost.adapter= adapter
//                }



                    //es xazebi uberavs arvici rato
//                    for (dataSnapshot in snapshot.children) {
//                        val image = dataSnapshot.getValue(UserImage::class.java)
//                        images.add(image!!)
//                    }
//                if (dataSnapshot.exists()) {
//
//                    for (dataSnapshot in dataSnapshot.children) {
//                        val post = dataSnapshot.getValue(Posts::class.java)
//                        posts.add(post!!)
//                    }
//                    binding.recyclerViewPost.adapter =RecyclerViewPostAdapter(posts)
//                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}