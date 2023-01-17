package com.example.corgo1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corgo1.Posts
import com.example.corgo1.UserImage
import com.example.corgo1.R
import com.example.corgo1.appFragments.FeedFragment

class RecyclerViewPostAdapter(

//    private val images: ArrayList<UserImage>,
    private val posts: ArrayList<Posts>,

    ) :
    RecyclerView.Adapter<RecyclerViewPostAdapter.PostViewHolder>() {


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val image : ImageView = itemView.findViewById(R.id.postPicture)
        val username : TextView = itemView.findViewById(R.id.username)
        val description : TextView = itemView.findViewById(R.id.postDescription)









    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent,false)
        return PostViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = posts[position]
//        Glide.with(context).load(images[position].userImages).into(holder.image)
        holder.username.text =currentPost.username
        holder.description.text = currentPost.description




    }

    override fun getItemCount():Int {
//        return  images.size,
        return posts.size
    }




}