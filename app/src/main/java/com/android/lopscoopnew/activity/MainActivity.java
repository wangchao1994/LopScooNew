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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private NewsFragment newsFragment;
    private MainFragment mainFragment;
    private RadioGroup radioGroup;
    private RadioButton news;
    private RadioButton main;
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
        newsFragment = new NewsFragment();
        mainFragment = new MainFragment();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(newsFragment);
        fragments.add(mainFragment);
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
