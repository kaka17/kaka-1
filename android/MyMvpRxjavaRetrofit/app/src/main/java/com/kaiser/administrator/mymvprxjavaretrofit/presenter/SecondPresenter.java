package com.kaiser.administrator.mymvprxjavaretrofit.presenter;

import com.kaiser.administrator.mymvprxjavaretrofit.baserx.RxSubscriber;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.SeconData;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.SecondBean;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.ThirdData;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.TwoResult;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.SecondContract;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.SecondContract.SecondPresenters;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
public class SecondPresenter extends SecondPresenters{


    @Override
    public void getLockList(String longitude, String latitude, String pageid, final boolean isRefresh) {
//        mRxManage.add(mModel.getListData(longitude, latitude, pageid).subscribe(new RxSubscriber<List<SecondBean>>(mContext,false) {
//
//            @Override
//            protected void _onNext(List<SecondBean> list) {
//                mView.showData(list);
//                mView.stopLoading();
//            }
//
//            @Override
//            protected void _onError(String message) {
//                mView.showErrorTip(message);
//            }
//        }));



//        mRxManage.add(mModel.getList(longitude,latitude,pageid).subscribe(new RxSubscriber<SeconData>(mContext,false) {
//            @Override
//            protected void _onNext(SeconData seconData) {
//                mView.showData(seconData);
//                mView.stopLoading();
//            }
//
//            @Override
//            protected void _onError(String message) {
//
//            }
//        }));

        mRxManage.add(mModel.getListw(longitude,latitude,pageid).subscribe(new RxSubscriber<SeconData>(mContext,false) {
            @Override
            protected void _onNext(SeconData twoResultSeconData) {
                mView.showDatas(twoResultSeconData,isRefresh);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));

    }



}
