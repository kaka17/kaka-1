package com.kaiser.administrator.mymvprxjavaretrofit.api;

import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInFo;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInfoBean;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GirlData;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.HttpResult;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.SeconData;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.SecondBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 描述：
 * Created by qyh on 2016/12/28.
 */
public interface ApiService {
    String NEAR_HOTELCHATROOM = "user.near_hotelchatroom";//根据经纬度获取周边酒店的聊天室
    String KAISERHUSHILIST = "stock/shall";//沪市股列表
    String KAISERSZSHILIST = "stock/hs";//沪深股


    /**
     * 图片URL   http://gank.io/api/data/福利/20/1
     * @param size
     * @param page
     * @return
     */
    @GET("data/福利/{size}/{page}")
    Observable<GirlData> getListData(
//            @Header("Cache-Control") String cacheControl,
            @Path("size") int size,
            @Path("page") int page);


    /*
    *  params.put("longitude", longitude);
        params.put("latitude", latitude);
        params.put("pageid",pageid);
    *
    * */

    @FormUrlEncoded
    @POST(NEAR_HOTELCHATROOM)
    Observable<HttpResult<List<SecondBean>>> getLockChat(@Field("longitude") String longitude, @Field("latitude") String latitude,
                            @Field("pageid") String pageid);
    @FormUrlEncoded
    @POST(KAISERHUSHILIST)
    Observable<SeconData> getLockChatByList(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST(KAISERSZSHILIST)
    Observable<GPInfoBean<List<GPInFo>>> getGPInfo(@FieldMap Map<String, String> fields);

}
