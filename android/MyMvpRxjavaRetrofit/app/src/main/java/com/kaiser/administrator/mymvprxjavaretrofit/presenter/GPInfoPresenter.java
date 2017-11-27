package com.kaiser.administrator.mymvprxjavaretrofit.presenter;

import com.kaiser.administrator.mymvprxjavaretrofit.baserx.RxSubscriber;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInFo;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInfoBean;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.GPInFoContract;

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
