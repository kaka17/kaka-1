package com.kaiser.administrator.mymvprxjavaretrofit.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiser.administrator.mymvprxjavaretrofit.R;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.BaseQuickAdapter;
import com.kaiser.administrator.mymvprxjavaretrofit.base.baseadapter.BaseViewHolder;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.ThirdData;
import com.kaiser.administrator.mymvprxjavaretrofit.myinterface.MyItemClickListener;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.DoubleUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public class SecondAdapter extends BaseQuickAdapter {
    public SecondAdapter(int layoutResId, List<ThirdData> data) {
        super(layoutResId, data);
    }
    private MyItemClickListener onClick;
    public   void setOnClickListener(MyItemClickListener onClick){
        this.onClick=onClick;
    }
    @Override
    protected void convert(BaseViewHolder helper, Object item, final int position) {
        ThirdData data= (ThirdData) item;
        TextView tvName = helper.getView(R.id.tvName);
        TextView tvPrice = helper.getView(R.id.tvPrice);
        TextView tvChang = helper.getView(R.id.tvChang);
        TextView tvCode = helper.getView(R.id.tvCode);
        TextView tvNum = helper.getView(R.id.tvNum);
       ImageView ivAdd= helper.getView(R.id.ivAdd);

        tvName.setText(data.getName());
        tvPrice.setText(DoubleUtil.getDoubleDecimal2(data.getTrade()));
        tvChang.setText(DoubleUtil.getDoubleDecimal2(data.getChangepercent()));
        tvCode.setText(data.getCode());
        if (data.getVolume()>9999){
            tvNum.setText( (int)(data.getVolume()/1000) + "/万");
        }else {
            tvNum.setText( data.getVolume()+"");
        }
        if(data.getChangepercent()>0){
            tvChang.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            tvPrice.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            tvCode.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            tvNum.setTextColor(mContext.getResources().getColor(R.color.colorAccent));

        }else {
            tvChang.setTextColor(mContext.getResources().getColor(R.color.main_color));
            tvPrice.setTextColor(mContext.getResources().getColor(R.color.main_color));
            tvCode.setTextColor(mContext.getResources().getColor(R.color.main_color));
            tvNum.setTextColor(mContext.getResources().getColor(R.color.main_color));
        }
        if (onClick!=null){
            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(v,position);
                }
            });
        }

    }
}
