package com.example.home.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.common.bean.NewsBean
import com.example.home.HomeFragment
import com.example.home.R
import java.text.SimpleDateFormat


//class HomeAdapter(arrayData: NewsBean ): RecyclerView.Adapter<RecyclerView.ViewHolder>(),View.OnClickListener {
//
//    private var listData: NewsBean = arrayData
//    private var itemClickListener: ItemClickListener? = null
//    var mContext: HomeFragment?=null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        var view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
//        return HomeViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is HomeViewHolder) {
////            holder.image?.let { Glide.with(mContext).load(listData.data[position].thumbnail_pic_s).into(
////                it
////            ) }
//            val sdf = SimpleDateFormat("yyyy-MM-dd")
//
//            var data:String=sdf.format(listData.datas[position].shareDate)
//            holder.date?.text=data
//            holder.name?.text = listData.datas[position].title
//            holder.author?.text = listData.datas[position].shareUser
//
//            holder.itemView.setOnClickListener {
//                itemClickListener!!.onItemClickListener(position)
//            }
//
//
//        }
//
//    }
//    override fun getItemCount(): Int {
//        return listData.datas.size
//    }
//
//    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var name: TextView? = null
//        var author: TextView? = null
//        var image:ImageView?=null
//        var date:TextView?=null
//        init {
//            name = itemView.findViewById(R.id.title)
//            author  = itemView.findViewById(R.id.author)
//            date  =itemView.findViewById(R.id.date)
//            image =itemView.findViewById(R.id.image)
//        }
//    }
//
//    fun setOnItemClickListener(itemClickListener: ItemClickListener) {
//        this.itemClickListener = itemClickListener
//    }
//
//
//    //???????????????
//    interface ItemClickListener {
//        fun onItemClickListener(position: Int)
//    }
//
//    override fun onClick(v: View?) {
//
//    }
//
//
//}
