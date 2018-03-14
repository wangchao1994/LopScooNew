package com.android.lopscoopnew.adapter;

import android.widget.ImageView;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.bean.NewData;
import com.bumptech.glide.Glide;
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
            Glide.with(mContext).load(item.getThumbnail()).into(iv);
        }else{
            Glide.with(mContext).load(R.mipmap.load_fail_rect).into(iv);
        }
        helper.addOnClickListener(R.id.ll_news_detail);

    }
}
