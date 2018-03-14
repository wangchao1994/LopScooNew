package com.android.lopscoopnew.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.base.BaseFragment;
import com.android.lopscoopnew.fragment.MainFragment;
import com.android.lopscoopnew.fragment.NewsFragment;
import com.android.lopscoopnew.fragment.PhotoFragment;
import com.android.lopscoopnew.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private NewsFragment newsFragment;
    private MainFragment mainFragment;
    private VideoFragment videoFragment;
    private PhotoFragment photoFragment;
    private RadioGroup radioGroup;
    private RadioButton news;
    private RadioButton main;
    private RadioButton video;
    private RadioButton photo;
    private List<BaseFragment> fragments;
    private int position;
    private Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.home_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        news = (RadioButton) findViewById(R.id.news);
        main = (RadioButton) findViewById(R.id.main);
        video = (RadioButton) findViewById(R.id.video);
        photo = (RadioButton) findViewById(R.id.photo);
        newsFragment = new NewsFragment();
        mainFragment = new MainFragment();
        videoFragment = new VideoFragment();
        photoFragment = new PhotoFragment();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(newsFragment);
        fragments.add(mainFragment);
        fragments.add(videoFragment);
        fragments.add(photoFragment);
        radioGroup.check(R.id.news);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.news:
                position = 0;
                break;
            case R.id.main:
                position = 1;
                break;
            case R.id.video:
                position = 2;
                break;
            case R.id.photo:
                position = 3;
                break;
        }
        Fragment fragment = getFragemnt();
        switchFragment(currentFragment,fragment);
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (from != to){
            currentFragment = to;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()){
                if (from != null){
                    fragmentTransaction.hide(from);
                }

                if (to != null){
                    fragmentTransaction.add(R.id.home_fl_content,to).commit();
                }
            }else{
                if (from != null){
                    fragmentTransaction.hide(from);
                }
                if (to != null){
                    fragmentTransaction.show(to).commit();
                }
            }
        }
    }

    private Fragment getFragemnt() {
        BaseFragment fragment = fragments.get(position);
        return fragment;
    }
}
