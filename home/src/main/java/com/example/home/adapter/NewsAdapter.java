package com.example.home.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.common.bean.DataX;
import com.example.home.R;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;


public class NewsAdapter extends BaseQuickAdapter<DataX, BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<DataX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, DataX item) {
        String author = (item.getAuthor() != null && item.getAuthor().length() > 0) ? item.getAuthor():item.getShareUser();
        String title = item.getTitle();
        title = title.replaceAll("&ldquo;","“").replaceAll("&rdquo;","”");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String data=formatter.format(item.getShareDate());
        helper.setText(R.id.author,author)
                .setText(R.id.title,title)
                .setText(R.id.date,data);
    }

}
