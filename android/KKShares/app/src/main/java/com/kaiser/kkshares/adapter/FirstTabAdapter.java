package com.kaiser.kkshares.adapter;



import android.widget.ImageView;

import com.kaiser.kkshares.R;
import com.kaiser.kkshares.base.baseadapter.BaseQuickAdapter;
import com.kaiser.kkshares.base.baseadapter.BaseViewHolder;
import com.kaiser.kkshares.bean.FirstBean;
import com.kaiser.kkshares.utils.ImageLoaderUtils;

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
