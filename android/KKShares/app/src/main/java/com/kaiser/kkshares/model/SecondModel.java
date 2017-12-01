package com.kaiser.kkshares.model;

import android.util.Log;

import com.kaiser.kkshares.api.HttpClient;
import com.kaiser.kkshares.baserx.RxSchedulers;
import com.kaiser.kkshares.bean.SeconData;
import com.kaiser.kkshares.bean.SecondBean;
import com.kaiser.kkshares.contract.SecondContract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/8/15.
 */
public class SecondModel implements SecondContract.SecondModels {

    @Override
    public Observable<List<SecondBean>> getListData(String 	key, String size, String pageid) {


//        Observable<HttpResult<List<SecondBean>>> lockChat =HttpClient.instance().service.getLockChat(longitude, latitude, pageid);
//
//        Observable<List<SecondBean>> compose = lockChat.map(new Func1<HttpResult<List<SecondBean>>, List<SecondBean>>() {
//            @Override
//            public List<SecondBean> call(HttpResult<List<SecondBean>> listHttpResult) {
//                return listHttpResult.getResult();
//            }
//        }).compose(RxSchedulers.<List<SecondBean>>io_main());
        return null;
    }

    @Override
    public Observable<SeconData> getList(String key, String size, String pageid) {
        Map<String, String> map=new HashMap<>();
        map.put("key",key);
        map.put("stock", "b");//a股
        map.put("page", pageid);
        map.put("type", size);
        Observable<SeconData> lockChatByList = HttpClient.instance().service.getLockChatByList(map);
        Log.e("TAGG", "----------->" + lockChatByList.toString());

        Observable<SeconData> compose = lockChatByList.map(new Func1<SeconData, SeconData>() {
            @Override
            public SeconData call(SeconData seconData) {
                return seconData;
            }
        }).compose(RxSchedulers.<SeconData>io_main());
        return compose;
    }

    @Override
    public Observable getListw(String key, String size, String pageid) {

        Map<String, String> map=new HashMap<>();
        map.put("key",key);
        map.put("stock", "a");//a股
        map.put("page", pageid);
        map.put("type", size);
        Observable<SeconData> lockChatByList = HttpClient.instance().service.getLockChatByList(map);
        Log.e("TAGG", "----------->" + lockChatByList.toString());
        Observable<SeconData> compose = lockChatByList.map(new Func1<SeconData, SeconData>() {
            @Override
            public SeconData call(SeconData seconData) {
                return seconData;
            }
        }).compose(RxSchedulers.<SeconData>io_main());

        return compose;
    }


}
