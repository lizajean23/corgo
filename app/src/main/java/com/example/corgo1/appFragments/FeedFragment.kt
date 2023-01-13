package com.example.corgo1.appFragments

import RecyclerViewPostAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.corgo1.Posts
import com.example.corgo1.R
import com.example.corgo1.databinding.FragmentFeedBinding

class FeedFragment:Fragment(R.layout.fragment_feed) {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerViewPostAdapter


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
        adapter = RecyclerViewPostAdapter(getData())
        binding.recyclerViewPost.adapter = adapter

    }
    private fun getData(): ArrayList<Posts> {
        val posts = ArrayList<Posts>()
        posts.add(
            Posts(
                "https://www.google.com/search?q=dog&sxsrf=AJOqlzXb-tiWTCBYfCX3dSjiLJEwXOTMXg:1673627436144&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiqh8WG_MT8AhXNO-wKHZtCCTAQ_AUoAXoECAEQAw&biw=1536&bih=754&dpr=1.25#imgrc=i8__JR5jsTLauM",
                "cu",
                "mshia"


            )
        )

        posts.add(
            Posts(
                "https://www.google.com/search?q=dog&sxsrf=AJOqlzXb-tiWTCBYfCX3dSjiLJEwXOTMXg:1673627436144&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiqh8WG_MT8AhXNO-wKHZtCCTAQ_AUoAXoECAEQAw&biw=1536&bih=754&dpr=1.25",
                "me",
                "woof"


            )
        )

        posts.add(
            Posts(
                "https://www.google.com/search?q=dog&sxsrf=AJOqlzXb-tiWTCBYfCX3dSjiLJEwXOTMXg:1673627436144&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiqh8WG_MT8AhXNO-wKHZtCCTAQ_AUoAXoECAEQAw&biw=1536&bih=754&dpr=1.25#imgrc=btQ8-aZ4x2YyMM",
                "bestdog",
                "kaia mze"


            )
        )

        return posts


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
