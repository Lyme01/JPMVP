package com.example.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.arouter.RouterUrl;
import com.example.home.DensityUtil;
import com.example.home.R;
import com.example.home.RoundImageView;

import java.util.ArrayList;
@Route(path = RouterUrl.Home.Search)
public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();
    private ViewPager viewpager;
    private LinearLayout ll_point_group;
    //ListView的使用
    //1.在布局文件中定义ListView
    //2.在代码中实例化ListView
    //3.准备数据
    //4.设置适配器-item布局-绑定数据

    private ArrayList<ImageView> imageViews;

    // 图片资源ID
    private final int[] imageIds = {
            R.drawable.ic_launcher_background,
            R.drawable.icon_like_article_not_selected,
            R.drawable.ic_paisa_loading,
           };

    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;
    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;

    // 图片标题集合
    private final String[] imageDescriptions = {
            "尚硅谷波河争霸赛！",
            "凝聚你我，放飞梦想！",
            "抱歉没座位了！",
            "7月就业名单全部曝光！",
            "平均起薪11345元"
    };

    private Handler handler = new Handler(){
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        ll_point_group = (LinearLayout) findViewById(R.id.pointgroup);


        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = new ArrayList<>();
        for (int i = 0 ;i < imageIds.length;i++){

            ImageView imageView = new RoundImageView(this);
            imageView.setBackgroundResource(imageIds[i]);

            //添加到集合中
            imageViews.add(imageView);

            //添加点-----------------------
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.menu_two_point_sel);
//            point.setImageResource(R.drawable.point_selector);
            //在代码中设置的都是像数-问题，在所有的手机上都是8个像数
            //把8px当成是dp-->px
            int width = DensityUtil.dip2px(this,8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,width);
            if(i==0){
                point.setEnabled(true); //显示红色
            }else{
                point.setEnabled(false);//显示灰色
                params.leftMargin = width;
            }


            point.setLayoutParams(params);

            ll_point_group.addView(point);
        }
        //4.设置适配器(PagerAdapter)-item布局-绑定数据

        viewpager.setAdapter(new MyPagerAdapter());
        //设置监听ViewPager页面的改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置中间位置
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍


        viewpager.setCurrentItem(item);


        //发消息
        handler.sendEmptyMessageDelayed(0,3000);

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         * @param position 当前页面的位置
         * @param positionOffset 滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position%imageViews.size();


            //把上一个高亮的设置默认-灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_point_group.getChildAt(realPosition).setEnabled(true);
            prePosition = realPosition;
        }

        /**
         当页面滚动状态变化的时候回调这个方法
         静止->滑动
         滑动-->静止
         静止-->拖拽

         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging = true;
                handler.removeCallbacksAndMessages(null);

            }else if(state == ViewPager.SCROLL_STATE_SETTLING){


            }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                isDragging = false;

                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }

        }
    }

    class MyPagerAdapter extends PagerAdapter {


        /**
         * 得到图片的总数
         * @return
         */
        @Override
        public int getCount() {
//            return imageViews.size();
            return Integer.MAX_VALUE;
        }

        /**
         * 相当于getView方法
         * @param container ViewPager自身
         * @param position 当前实例化页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            int realPosition = position%imageViews.size();

            final ImageView imageView =  imageViews.get(realPosition);
            if (imageView.getParent()!=null){
                ((ViewPager)imageView.getParent()).removeView(imageView);
            }
            container.addView(imageView);//添加到ViewPager中


            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = (int) v.getTag()%imageViews.size();
                    String text = imageDescriptions[position];


                }
            });

            return imageView;
        }

        /**
         * 比较view和object是否同一个实例
         * @param view 页面
         * @param object  这个方法instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        /**
         * 释放资源
         * @param container viewpager
         * @param position 要释放的位置
         * @param object 要释放的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

//            container.removeView((View) object);

        }
    }
}