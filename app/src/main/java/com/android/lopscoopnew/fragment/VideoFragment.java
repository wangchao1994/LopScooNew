package com.android.lopscoopnew.fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.base.BaseFragment;

/**
 * Created by wangchao on 18-3-14.
 */

public class VideoFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_main, null);
        return view;
    }
}
