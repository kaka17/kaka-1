package com.kaiser.kkshares.contract;

import com.kaiser.kkshares.base.BaseModel;
import com.kaiser.kkshares.base.BasePresenter;
import com.kaiser.kkshares.base.BaseView;
import com.kaiser.kkshares.bean.SeconData;
import com.kaiser.kkshares.bean.SecondBean;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/8/14.
 */
public interface SecondContract {

    interface SecondModels extends BaseModel {
        //请求获取数据
        Observable<List<SecondBean>> getListData(String longitude, String  latitude, String pageid);
        Observable<SeconData> getList(String longitude, String  latitude,String pageid);
        Observable< SeconData > getListw(String longitude, String  latitude,String pageid);

    }

    interface SecondView extends BaseView{
        void showData( List<SecondBean> list);
        void showData(SeconData seconData);
        void showDatas(SeconData conData,boolean isRefresh);
    }

    abstract static class SecondPresenters extends BasePresenter<SecondView,SecondModels> {
        public abstract void getLockList(String longitude,String latitude,String pageid,boolean isRefresh);
    }

}
