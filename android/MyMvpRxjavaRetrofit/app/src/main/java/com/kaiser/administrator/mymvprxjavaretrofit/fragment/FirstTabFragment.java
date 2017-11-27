package com.kaiser.administrator.mymvprxjavaretrofit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaiser.administrator.mymvprxjavaretrofit.R;
import com.kaiser.administrator.mymvprxjavaretrofit.adapter.FirstTabAdapter;
import com.kaiser.administrator.mymvprxjavaretrofit.base.BaseFragment;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.BaseQuickAdapter;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.OnItemClickListener;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.FirstBean;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.FirstContract;
import com.kaiser.administrator.mymvprxjavaretrofit.model.FirstModel;
import com.kaiser.administrator.mymvprxjavaretrofit.presenter.FirstPresenter;
import com.kaiser.administrator.mymvprxjavaretrofit.view.LoadingDialog;
import com.kaiser.administrator.mymvprxjavaretrofit.view.refresh.NormalRefreshViewHolder;
import com.kaiser.administrator.mymvprxjavaretrofit.view.refresh.RefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/11.
 */
public class FirstTabFragment extends BaseFragment<FirstPresenter,FirstModel> implements FirstContract.View, BaseQuickAdapter.RequestLoadMoreListener, RefreshLayout.RefreshLayoutDelegate {

    private  int SIZE=20;
    private static final int STARTPAGE=1;
    @BindView(R.id.rv_content)
    public RecyclerView rv_content;
    @BindView(R.id.refresh)
    public RefreshLayout refreshLayout;


    private Context mContext;
    private FirstTabAdapter mFirstTabAdapter;

    @Override
    protected int getLayoutResource() {
        mContext=getActivity();
        return R.layout.fragment_first;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {

        mFirstTabAdapter = new FirstTabAdapter(R.layout.item_first,null);
        rv_content.setLayoutManager(new GridLayoutManager(mContext,2));
        rv_content.setHasFixedSize(true);
        //设置适配器加载动画
        mFirstTabAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv_content.setAdapter(mFirstTabAdapter);
        //设置适配器可以上拉加载
        mFirstTabAdapter.setOnLoadMoreListener(this);
        //设置下拉、上拉
        refreshLayout.setDelegate(this);
        refreshLayout.setRefreshViewHolder(new NormalRefreshViewHolder(mContext,true));

        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);

        //条目的点击事件
        rv_content.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                System.out.println("position===="+position);
            }
        });
    }

    @Override
    protected void setMyOnClickListener() {

    }

    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {
        System.out.println("onRefreshLayoutBeginRefreshing===");
        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        SIZE+=20;
        mPresenter.getFirstListDataRequest(SIZE,STARTPAGE);
        return true;
    }

    @Override
    public void onLoadMoreRequested() {
        //   BaseQuickAdapter的上拉加载更多方法，和onRefreshLayoutBeginLoadingMore使用其中一个就可以
    }

    @Override
    public void showListData(List<FirstBean> listData) {
        mFirstTabAdapter.setNewData(listData);
        refreshLayout.endRefreshing();
        refreshLayout.endLoadingMore();
    }

    @Override
    public void showLoading(String title) {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();
    }
    //加载失败提示，根据需要自己处理
    @Override
    public void showErrorTip(String msg) {
        showLongToast("加载失败");
    }
}
