package com.example.corgo1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.corgo1.Posts
import com.example.corgo1.R

class RecyclerViewPostAdapter(private val Post: ArrayList<Posts>):
    RecyclerView.Adapter<RecyclerViewPostAdapter.PostViewHolder>() {


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pfp : ImageView = itemView.findViewById(R.id.Pfp)
        private val picture : ImageView = itemView.findViewById(R.id.postPicture)
        private val username : TextView = itemView.findViewById(R.id.username)
        private val postDescription : TextView = itemView.findViewById(R.id.postDescription)

        fun setData(post: Posts) {
            Glide.with(itemView).load(post.imageUrl).into(pfp)
            Glide.with(itemView).load(post.imageUrl).into(picture)
            username.text = post.username
            postDescription.text = post.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_posts,parent,false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setData(Post[position])

    }

    override fun getItemCount() = Post.size

}