package com.kaiser.administrator.mymvprxjavaretrofit.presenter;

import com.kaiser.administrator.mymvprxjavaretrofit.R;
import com.kaiser.administrator.mymvprxjavaretrofit.baserx.RxSubscriber;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.FirstBean;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.FirstContract;

import java.util.List;

import rx.Observable;
import rx.Subscription;


/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public class FirstPresenter extends FirstContract.Presenter{

    @Override
    public void getFirstListDataRequest(int size, int page) {

        mRxManage.add(mModel.getListData(size,page).subscribe(new RxSubscriber<List<FirstBean>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(List<FirstBean> firstBeen) {
                mView.showListData(firstBeen);
                mView.stopLoading();
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    public void getiii(){
        Observable<List<FirstBean>> listData = mModel.getListData(1, 10);
        Subscription subscribe = mModel.getListData(1, 10).subscribe();
        mRxManage.add(mModel.getListData(1,10).subscribe(new RxSubscriber<List<FirstBean>>(mContext,false) {
            @Override
            protected void _onNext(List<FirstBean> firstBeans) {

            }

            @Override
            protected void _onError(String message) {

            }
        }));
    }

}
