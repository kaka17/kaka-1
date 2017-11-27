package com.kaiser.administrator.mymvprxjavaretrofit.adapter;



import android.widget.ImageView;

import com.kaiser.administrator.mymvprxjavaretrofit.R;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.BaseQuickAdapter;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.BaseViewHolder;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.FirstBean;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.ImageLoaderUtils;

import java.util.List;



/**
 * 描述：
 * Created by qyh on 2016/12/30.
 */
public class FirstTabAdapter extends BaseQuickAdapter {
    
    public FirstTabAdapter(int layoutResId, List<FirstBean> listData) {
        super(layoutResId, listData);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item, int position) {
        FirstBean data=(FirstBean)item;
        ImageLoaderUtils.display(mContext, (ImageView) helper.getView(R.id.iv_item_picture)
                , data.getUrl());
    }
}
