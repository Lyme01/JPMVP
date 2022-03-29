package com.example.home.activity

import android.annotation.SuppressLint
import android.widget.LinearLayout
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.example.common.arouter.RouterUrl
import com.example.home.DensityUtil
import com.example.home.R
import com.example.home.RoundImageView

@Route(path = RouterUrl.Home.Search)
class BannerActivity : AppCompatActivity() {
    private var viewpager: ViewPager? = null
    private var ll_point_group: LinearLayout? = null

    //ListView的使用
    //1.在布局文件中定义ListView
    //2.在代码中实例化ListView
    //3.准备数据
    //4.设置适配器-item布局-绑定数据
    private var imageViews: ArrayList<ImageView>? = null

    private val imageList= mutableListOf(
        "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg",
        "https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg",
        "https://img.zcool.cn/community/01270156fb62fd6ac72579485aa893.jpg"
    )

    /**
     * 上一次高亮显示的位置
     */
    private var prePosition = 0

    /**
     * 是否已经滚动
     */
    private var isDragging = false


    private val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val item = viewpager!!.currentItem + 1
            viewpager?.currentItem = item
            //延迟发消息
            this.sendEmptyMessageDelayed(0, 4000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        viewpager = findViewById<View>(R.id.viewpager) as ViewPager
        ll_point_group = findViewById<View>(R.id.pointgroup) as LinearLayout


        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = ArrayList()
        for (i in 0 until imageList.size) {
            val imageView = RoundImageView(this)
            Glide.with(this)
                .load(imageList[i])
                .into(imageView)
                imageView.scaleType=ImageView.ScaleType.FIT_XY
            //添加到集合中
            imageViews!!.add(imageView)

            //添加点-----------------------
            val point = ImageView(this)
            point.setBackgroundResource(R.drawable.menu_two_point_sel)
            //在代码中设置的都是像数-问题，在所有的手机上都是8个像数
            //把8px当成是dp-->px
            val width: Int = DensityUtil.dip2px(this, 8F)
            val params = LinearLayout.LayoutParams(width, width)
            if (i == 0) {
                point.isSelected = true //显示红色
            } else {
                point.isSelected = false //显示灰色
                params.leftMargin = width
            }
            point.layoutParams = params
            ll_point_group!!.addView(point)
        }
        //4.设置适配器(PagerAdapter)-item布局-绑定数据
        viewpager?.adapter =  ViewAdapter(imageViews!!)
        //设置监听ViewPager页面的改变
        viewpager!!.addOnPageChangeListener(object :OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            /**
             * 当某个页面被选中了的时候回调
             * @param position 被选中页面的位置
             */
            override fun onPageSelected(position: Int) {
                val realPosition: Int = position % imageViews!!.size
                //把上一个高亮的设置默认-灰色
                ll_point_group!!.getChildAt(prePosition).isSelected = false
                //当前的设置为高亮-红色
                ll_point_group!!.getChildAt(realPosition).isSelected = true
                prePosition = realPosition
            }

            /**
             * 当页面滚动状态变化的时候回调这个方法
             * 静止->滑动
             * 滑动-->静止
             * 静止-->拖拽
             *
             */
            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isDragging = true
                    handler.removeCallbacksAndMessages(null)
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                    isDragging = false
                    handler.removeCallbacksAndMessages(null)
                    handler.sendEmptyMessageDelayed(0, 4000)
                }
            }

        })


//        //设置中间位置
//        val item: Int =
//            Int.MAX_VALUE / 2 - Int.MAX_VALUE / 2 % imageViews!!.size //要保证imageViews的整数倍
//        viewpager?.currentItem = item


        //发消息
        handler.sendEmptyMessageDelayed(0, 3000)
    }



}


