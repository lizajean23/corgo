package com.example.corgo1.appFragments


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corgo1.Posts
import com.example.corgo1.R
import com.example.corgo1.adapter.RecyclerViewPostAdapter
import com.example.corgo1.databinding.FragmentFeedBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataList: ArrayList<Posts>
    private lateinit var adapter: RecyclerViewPostAdapter


    private lateinit var databaseReference: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View {

        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewPost.layoutManager = LinearLayoutManager(requireContext())
        dataList = ArrayList()
        adapter = RecyclerViewPostAdapter(requireContext(), dataList)
        binding.recyclerViewPost.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts")


        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(false)
        builder.setView(R.layout.loading_layout)
        val dialog = builder.create()
        dialog.show()

        databaseReference.addValueEventListener(object : ValueEventListener {


            override fun onDataChange(snapshot: DataSnapshot) {

                dataList.clear()
                if (snapshot.exists()) {
                    for (dataSnapShot in snapshot.children) {
                        val dataClass = dataSnapShot.getValue(Posts::class.java)
                        dataList.add(dataClass!!)
                    }
                    binding.recyclerViewPost.adapter =
                        RecyclerViewPostAdapter(requireContext(), dataList)
                    dialog.dismiss()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "cool", Toast.LENGTH_SHORT).show()
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}