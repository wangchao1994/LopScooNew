package com.android.lopscoopnew.adapter;

import android.widget.ImageView;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.bean.NewData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by wangchao on 18-3-13.
 */

public class NewsAdapter extends BaseQuickAdapter<NewData.DataBean,BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.item_news_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewData.DataBean item) {
        String title = item.getTitle();
        if (title != null){
            helper.setText(R.id.tv_news_detail_title,item.getTitle());
        }
        String postUserName = item.getPostUserName();
        if (postUserName != null){
            //helper.setText(R.id.tv_news_clicknum,item.getClickNum());
            helper.setText(R.id.tv_news_clicknum,item.getPostUserName());
        }
        String onlineTime = item.getOnlineTime();
        if (onlineTime != null){
            helper.setText(R.id.tv_news_detail_date,item.getOnlineTime());
        }
        String thumbnail = item.getThumbnail();
        ImageView iv = helper.getView(R.id.iv_news_detail_pic);
        if (thumbnail != null){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.mipmap.load_fail_rect)
                    .error(R.mipmap.load_fail_rect)
                    .fallback(R.mipmap.load_fail_rect)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(mContext).load(item.getThumbnail()).apply(requestOptions).into(iv);
        }
        helper.addOnClickListener(R.id.ll_news_detail);

    }
}
/*
RequestOptions requestOptions = new RequestOptions()
        .placeholder(R.mipmap.placeholder) //加载中图片
        .error(R.mipmap.error) //加载失败图片
        .fallback(R.mipmap.fallback) //url为空图片
        .centerCrop() // 填充方式
        .override(600,600) //尺寸
        .transform(new CircleCrop()) //圆角
        .priority(Priority.HIGH) //优先级
        .diskCacheStrategy(DiskCacheStrategy.NONE); //缓存策略，后面详细介绍

Glide.with(this).load(IMG_URL1).apply(requestOptions).into(testIv1);
Glide.with(this).load(IMG_URL2).apply(requestOptions).into(testIv2);

 */