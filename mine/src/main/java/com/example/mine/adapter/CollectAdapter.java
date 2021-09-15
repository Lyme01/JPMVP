package com.example.mine.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.common.bean.DataX;
import com.example.mine.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;


public class CollectAdapter extends BaseQuickAdapter<DataX, BaseViewHolder> {
    public CollectAdapter(int layoutResId, @Nullable List<DataX> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, DataX item) {

        helper.setText(R.id.item_article_author, item.getAuthor())
                .setText(R.id.title, item.getTitle())
                .setText(R.id.item_article_time, item.getNiceDate());
        helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);

    }

}
