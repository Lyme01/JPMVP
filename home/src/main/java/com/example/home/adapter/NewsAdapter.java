package com.example.home.adapter;


import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.common.bean.DataX;
import com.example.home.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * @author wwq
 */
public class NewsAdapter extends BaseQuickAdapter<DataX, BaseViewHolder> {
    private boolean isCollected = false;
    public NewsAdapter(int layoutResId, @Nullable List<DataX> data) {
        super(layoutResId, data);
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, DataX item) {



        helper.setText(R.id.item_article_author,item.getAuthor())
                .setText(R.id.title,item.getTitle())
                .setText(R.id.item_article_time,item.getNiceDate());

        if (item.getCollect()|| isCollected) {
                    helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_select);
        } else {
            helper.setImageResource(R.id.item_article_like, R.drawable.icon_like_article_not_selected);
        }
    }

}
