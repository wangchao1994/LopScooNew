package com.android.lopscoopnew.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.base.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * Created by wangchao on 18-3-13.
 */

public class NewsFragment extends BaseFragment {
    private MagicIndicator magic_indicator;
    private ViewPager viewPager;
    private String[] tabs_name;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_news, null);
        magic_indicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        viewPager = (ViewPager) view.findViewById(R.id.main_viewpager);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        tabs_name = getResources().getStringArray(R.array.tabs_name);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        CommonNavigatorAdapter commonNavigatorAdapter = new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs_name == null ? 0 : tabs_name.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(tabs_name[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return linePagerIndicator;
            }
        };
        commonNavigator.setAdapter(commonNavigatorAdapter);
        magic_indicator.setNavigator(commonNavigator);
        NewsViewPageAdapter newsViewPageAdapter = new NewsViewPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(newsViewPageAdapter);
        ViewPagerHelper.bind(magic_indicator,viewPager);
    }

    class NewsViewPageAdapter extends FragmentStatePagerAdapter{

        public NewsViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           NewsDetailFragment newsDetailFragment = NewsDetailFragment.newInstance(tabs_name[position]);
            return newsDetailFragment;
          //  return new NewsDetailFragment(tabs_name[position]);
        }

        @Override
        public int getCount() {
            return tabs_name == null ? 0 : tabs_name.length;
        }
    }
}
