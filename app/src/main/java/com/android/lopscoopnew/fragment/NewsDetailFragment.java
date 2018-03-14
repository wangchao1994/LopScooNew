package com.android.lopscoopnew.fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.lopscoopnew.R;
import com.android.lopscoopnew.activity.WebViewActivity;
import com.android.lopscoopnew.adapter.NewsAdapter;
import com.android.lopscoopnew.bean.NewData;
import com.android.lopscoopnew.net.NetConfig;
import com.android.lopscoopnew.net.QClient;
import com.android.lopscoopnew.net.QService;
import com.android.lopscoopnew.utils.NetUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangchao on 18-3-13.
 */

public class NewsDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static Bundle args;
    private SwipeRefreshLayout srl;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    //频道的类型
    private String type;
    /*
    public NewsDetailFragment() {
    }

    public NewsDetailFragment(String type) {
        this.type = type;
    }*/
    public static NewsDetailFragment newInstance(String type) {
        args = new Bundle();
        NewsDetailFragment fragment = new NewsDetailFragment();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  initView();
    }

    private View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news_data, null);
        srl = (SwipeRefreshLayout)view.findViewById(R.id.srl);
        srl.setColorSchemeColors(Color.RED,Color.RED);
        srl.setOnRefreshListener(this);
        newsAdapter = new NewsAdapter();
        newsAdapter.setEnableLoadMore(true);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_new_detail);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter != null){
                    NewData.DataBean dataBean = (NewData.DataBean) adapter.getData().get(position);
                    String url = dataBean.getClickUrl();
                    Log.d("wchao url","url="+url);
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", url);
                    getContext().startActivity(intent);
                }
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateData();
    }

    private void updateData() {
        srl.setRefreshing(true);
        boolean hasNetWork = NetUtils.hasNetWork(getContext());
        if (hasNetWork){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    QClient.getInstance().create(QService.class, NetConfig.BASEURL)
                            .getNewsData("test",type)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<NewData>() {
                                @Override
                                public void accept(NewData newData) throws Exception {
                                    List<NewData.DataBean> data = newData.getData();
                                    Log.d("news data", "newsData----->" + newData);
                                    if (data != null){
                                        newsAdapter.setNewData(data);
                                    }
                                    srl.setRefreshing(false);
                                }
                            });

                }
            }).start();
        }else{
            Toast.makeText(getActivity().getApplicationContext(),"Please Check NetWork!",Toast.LENGTH_SHORT).show();
            srl.setRefreshing(false);
        }

    }

    @Override
    public void onRefresh() {
        updateData();
    }
}
