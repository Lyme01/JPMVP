package com.example.home.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.home.R
import com.example.common.bean.NewsBean
import com.example.home.HomeFragment


class HomeAdapter(arrayData: NewsBean, var mContext: HomeFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData: NewsBean = arrayData


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder) {
            holder.image?.let { Glide.with(mContext).load(listData.data[position].thumbnail_pic_s).into(it) }
            holder.name?.text = listData.data[position].title
            holder.id?.text =  listData.data[position].author_name
        }

    }
    override fun getItemCount(): Int {
        return listData.data.size;
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView? = null
        var id: TextView? = null
        var image:ImageView?=null
        init {
            name = itemView.findViewById(R.id.title)
            id   = itemView.findViewById(R.id.author)
            image =itemView.findViewById(R.id.image)
        }
    }
}
