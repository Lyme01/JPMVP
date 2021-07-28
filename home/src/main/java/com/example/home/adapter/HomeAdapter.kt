package com.example.home.adapter


import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.bean.NewsBean
import com.example.home.HomeFragment
import com.example.home.R


class HomeAdapter(arrayData: NewsBean, var mContext: HomeFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>(),View.OnClickListener {

    private var listData: NewsBean = arrayData
    private var itemClickListener: ItemClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeViewHolder) {
            holder.image?.let { Glide.with(mContext).load(listData.data[position].thumbnail_pic_s).into(
                it
            ) }
            holder.name?.text = listData.data[position].title
            holder.author?.text =  listData.data[position].author_name
            holder.date?.text=listData.data[position].date
            holder.itemView.setOnClickListener {
                itemClickListener!!.onItemClickListener(position)
            }


        }

    }
    override fun getItemCount(): Int {
        return listData.data.size;
    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView? = null
        var author: TextView? = null
        var image:ImageView?=null
        var date:TextView?=null
        init {
            name = itemView.findViewById(R.id.title)
            author  = itemView.findViewById(R.id.author)
            date  =itemView.findViewById(R.id.date)
            image =itemView.findViewById(R.id.image)
        }
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    //自定义接口
    interface ItemClickListener {
        fun onItemClickListener(position: Int)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }


}
