package com.example.home.activity

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup

import android.widget.Toast



import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager







/**
 * @author wwq
 * @description:
 * @date :2021/12/11
 */

 class ViewAdapter(private val imageList:ArrayList<ImageView>) : PagerAdapter() {
    /**
     * 得到图片的总数
     * @return
     */
    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    /**
     * 相当于getView方法
     * @param container ViewPager自身
     * @param position 当前实例化页面的位置
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val realPosition: Int = position % imageList?.size
        val imageView: ImageView = imageList[realPosition]
        if (imageView.parent != null) {
            (imageView.parent as ViewPager).removeView(imageView)
        }
        container.addView(imageView) //添加到ViewPager中
        imageView.tag = position-1
        imageView.setOnClickListener { v ->

        }
        return imageView
    }

    /**
     * 比较view和object是否同一个实例
     * @param view 页面
     * @param object  这个方法instantiateItem返回的结果
     * @return
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    /**
     * 释放资源
     * @param container viewpager
     * @param position 要释放的位置
     * @param object 要释放的页面
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
    }
}