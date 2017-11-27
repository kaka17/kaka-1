package com.kaiser.administrator.mymvprxjavaretrofit.contract;

import com.kaiser.administrator.mymvprxjavaretrofit.base.BaseModel;
import com.kaiser.administrator.mymvprxjavaretrofit.base.BasePresenter;
import com.kaiser.administrator.mymvprxjavaretrofit.base.BaseView;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.FirstBean;

import java.util.List;

import rx.Observable;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public interface FirstContract {

    interface Model extends BaseModel {
        //请求获取数据
        Observable<List<FirstBean>> getListData(int size, int page);
    }
    interface View extends BaseView {
        //返回获取的数据
        void showListData(List<FirstBean> listData);
    }

    abstract static class Presenter extends BasePresenter<View,Model> {
        public abstract  void getFirstListDataRequest(int size,int page);
    }
}
