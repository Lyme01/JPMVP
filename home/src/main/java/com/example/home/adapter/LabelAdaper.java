package com.example.home.adapter;

import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.arouter.RouterUrl;
import com.example.common.bean.HotKeyBean;
import com.example.common.util.CommonUtils;
import com.example.home.R;
import com.zhengsr.tablib.view.adapter.LabelFlowAdapter;

import java.math.RoundingMode;
import java.util.List;

public class LabelAdaper extends LabelFlowAdapter<HotKeyBean> {

    public LabelAdaper(int layoutId, List<HotKeyBean> data) {
        super(layoutId, data);
    }

    @Override
    public void bindView(View view, HotKeyBean data, int position) {
        setText(view,R.id.item_text,data.getName())
                .setTextColor(view, R.id.item_text, Color.WHITE);
                view.setBackground(CommonUtils.getColorDrawable(10));
    }

    @Override
    public void onItemClick(View view, HotKeyBean data, int position) {
        super.onItemClick(view, data, position);
        ARouter.getInstance().build(RouterUrl.Home.Detail).withString("word",data.getName()).navigation();
    }
}