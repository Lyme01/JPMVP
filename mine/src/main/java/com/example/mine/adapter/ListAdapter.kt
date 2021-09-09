package com.example.mine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mine.R

class ListAdapter(var context: Context?): RecyclerView.Adapter<ListAdapter.MyHolder>() {

    private val data= listOf("111","2222")
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var textView:TextView?=null
        init {
            textView =itemView.findViewById(R.id.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.textView?.text=data[position]
    }

    override fun getItemCount(): Int {
       return data.size
    }
}