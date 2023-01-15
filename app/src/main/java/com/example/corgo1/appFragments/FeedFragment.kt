package com.example.corgo1.appFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.corgo1.Posts
import com.example.corgo1.R
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
    private lateinit var posts :ArrayList<Posts>
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

        binding.recyclerViewPost.layoutManager = LinearLayoutManager(activity)
        posts = arrayListOf()
        databaseReference = FirebaseDatabase.getInstance().getReference("userImages")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val image = dataSnapshot.getValue(Posts::class.java) ?: return
                        posts.add(image!!)
                    }
                    binding.recyclerViewPost.adapter =
                        RecyclerViewPostAdapter(posts, this@FeedFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            }
        })



        binding.recyclerViewPost.setHasFixedSize(true)

    }


        override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
