package com.example.corgo1.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corgo1.Posts
import com.example.corgo1.R

class RecyclerViewPostAdapter(

    private val context: Context,
    private val posts: ArrayList<Posts>,

    ) :
    RecyclerView.Adapter<RecyclerViewPostAdapter.PostViewHolder>() {


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username : TextView = itemView.findViewById(R.id.username)
        val description : TextView = itemView.findViewById(R.id.postDescription)
        val image :ImageView = itemView.findViewById(R.id.postPicture)
        val pfp : ImageView = itemView.findViewById(R.id.Pfp)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent,false)
        return PostViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        Glide.with(context).load(posts[position].image).into(holder.image)
        holder.username.text = posts[position].username
        holder.description.text = posts[position].description
        Glide.with(context).load(posts[position].pfp).into(holder.pfp)





    }

    override fun getItemCount():Int {
        return posts.size
    }




}