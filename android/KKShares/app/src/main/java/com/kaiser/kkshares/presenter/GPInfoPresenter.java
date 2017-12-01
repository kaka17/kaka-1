package com.kaiser.kkshares.presenter;

import com.kaiser.kkshares.baserx.RxSubscriber;
import com.kaiser.kkshares.bean.GPInFo;
import com.kaiser.kkshares.bean.GPInfoBean;
import com.kaiser.kkshares.contract.GPInFoContract;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public class GPInfoPresenter extends GPInFoContract.GPInfoPresenters {
    @Override
    public void getGpInfoData(String gid, String key) {
        mRxManage.add(mModel.getData(gid,key).subscribe(new RxSubscriber<GPInfoBean<List<GPInFo>>>(mContext,true) {
            @Override
            protected void _onNext(GPInfoBean<List<GPInFo>> listGPInfoBean) {
                mView.showData(listGPInfoBean);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
