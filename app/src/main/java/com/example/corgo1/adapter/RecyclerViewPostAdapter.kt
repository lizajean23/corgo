package com.example.corgo1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corgo1.UserImage
import com.example.corgo1.R
import com.example.corgo1.appFragments.FeedFragment

class RecyclerViewPostAdapter(

    private val Post: ArrayList<UserImage>,
    private val context: FeedFragment
    ) :
    RecyclerView.Adapter<RecyclerViewPostAdapter.PostViewHolder>() {


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.postPicture)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent,false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Glide.with(context).load(Post[position].userImage).into(holder.image)
    }

    override fun getItemCount():Int {
        return  Post.size
    }



}