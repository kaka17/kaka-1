package com.kaiser.administrator.mymvprxjavaretrofit.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiser.administrator.mymvprxjavaretrofit.R;
import com.kaiser.administrator.mymvprxjavaretrofit.api.HttpClient;
import com.kaiser.administrator.mymvprxjavaretrofit.base.AppManager;
import com.kaiser.administrator.mymvprxjavaretrofit.base.BaseActivity;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInFo;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInFoDataBean;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInfoBean;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.GPInfoPic;
import com.kaiser.administrator.mymvprxjavaretrofit.configs.Config;
import com.kaiser.administrator.mymvprxjavaretrofit.contract.GPInFoContract;
import com.kaiser.administrator.mymvprxjavaretrofit.model.GPInfoModel;
import com.kaiser.administrator.mymvprxjavaretrofit.presenter.GPInfoPresenter;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.DoubleUtil;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.ImageLoaderUtils;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.LogUtil;
import com.kaiser.administrator.mymvprxjavaretrofit.utils.Utils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/17.
 */
public class KaiserGuInfoActivity extends BaseActivity<GPInfoPresenter,GPInfoModel> implements GPInFoContract.GPInfoViews,View.OnClickListener{
    @BindView(R.id.tvBack)
    TextView tvBack;
     @BindView(R.id.tvName)
    TextView tvName;
     @BindView(R.id.tvSearsh)
    TextView tvSearsh;
    @BindView(R.id.ivPicByNew)
    ImageView ivPicByNew;
     @BindView(R.id.ivPicByDay)
    ImageView ivPicByDay;
     @BindView(R.id.ivPicByWeek)
    ImageView ivPicByWeek;
     @BindView(R.id.ivPicByMonth)
    ImageView ivPicByMonth;
    @BindView(R.id.tvPirce)
    TextView tvPirce;
    @BindView(R.id.tvZhang)
    TextView tvZhang;
    @BindView(R.id.tvGaoPice)
    TextView tvGaoPice;
    @BindView(R.id.tvDiPice)
    TextView tvDiPice;
    @BindView(R.id.tvNums)
    TextView tvNums;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvKais)
    TextView tvKais;
     @BindView(R.id.tvTimeK)
    TextView tvTimeK;
     @BindView(R.id.tvDayK)
    TextView tvDayK;
     @BindView(R.id.tvWeekK)
    TextView tvWeekK;
     @BindView(R.id.tvMonthK)
    TextView tvMonthK;
    @BindView(R.id.tvRefreash)
    TextView tvRefreash;

    private  GPInfoPic picBean;
    private  String code;
    @Override
    public int getLayoutId() {
        return R.layout.kaisergu_info;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        Bundle extras = getIntent().getExtras();
        code  = extras.getString(Config.GPCODE);
        Log.e("TAG","------------->"+code);
        mPresenter.getGpInfoData(code, HttpClient.KAISERKEY);


    }

    @Override
    public void setOnClicks() {
        tvBack.setOnClickListener(this);
        tvSearsh.setOnClickListener(this);
        tvTimeK.setOnClickListener(this);
        tvDayK.setOnClickListener(this);
        tvWeekK.setOnClickListener(this);
        tvMonthK.setOnClickListener(this);
        tvRefreash.setOnClickListener(this);

    }

    @Override
    public void showData(GPInfoBean<List<GPInFo>> data) {
        LogUtil.logE(data.toString());
        GPInFoDataBean dataBean = data.getResult().get(0).getData();
         picBean = data.getResult().get(0).getGopicture();
        tvName.setText(dataBean.getName());
        tvPirce.setText(DoubleUtil.getDoubleDecimal2(dataBean.getNowPri()));
        tvZhang.setText(DoubleUtil.getDoubleDecimal2(dataBean.getIncrePer()) + "%");
        tvGaoPice.setText(DoubleUtil.getDoubleDecimal2(dataBean.getTodayMax()));
        tvDiPice.setText(DoubleUtil.getDoubleDecimal2(dataBean.getTodayMin()));
        if (dataBean.getTraNumber()>9999){
            tvNums.setText(DoubleUtil.getDoubleDecimal2(dataBean.getTraNumber()/1000)+"/万");
        }else {
            tvNums.setText(dataBean.getTraNumber()+"");
        }
        tvMoney.setText(Utils.getDan(dataBean.getTraAmount()));
        tvKais.setText(dataBean.getTodayStartPri());
        tvTimeK.setTextColor(getResources().getColor(R.color.load_red));
        ImageLoaderUtils.disPlayGif(mContext, ivPicByNew, data.getResult().get(0).getGopicture().getMinurl());
        ImageLoaderUtils.disPlayGif(mContext, ivPicByDay, data.getResult().get(0).getGopicture().getDayurl());
        ImageLoaderUtils.disPlayGif(mContext, ivPicByWeek, data.getResult().get(0).getGopicture().getWeekurl());
        ImageLoaderUtils.disPlayGif(mContext, ivPicByMonth, data.getResult().get(0).getGopicture().getMonthurl());


        if (dataBean.getYestodEndPri()<dataBean.getNowPri()){
            tvPirce.setTextColor(getResources().getColor(R.color.load_red));
            tvZhang.setTextColor(getResources().getColor(R.color.load_red));
        }else {
            tvPirce.setTextColor(getResources().getColor(R.color.main_color));
        }

        if (dataBean.getTodayMax()>dataBean.getYestodEndPri()){
            tvGaoPice.setTextColor(getResources().getColor(R.color.load_red));
        }else {
            tvGaoPice.setTextColor(getResources().getColor(R.color.main_color));
        }

        if (dataBean.getTodayMin()>dataBean.getYestodEndPri()){
            tvDiPice.setTextColor(getResources().getColor(R.color.load_red));
        }else {
            tvDiPice.setTextColor(getResources().getColor(R.color.main_color));
        }
        if (dataBean.getIncrePer()>0){
            tvZhang.setTextColor(getResources().getColor(R.color.load_red));
        }else {
            tvZhang.setTextColor(getResources().getColor(R.color.main_color));
        }

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        showLongToast(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBack:
//                finish();
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.tvSearsh:
                break;
            case R.id.tvTimeK:
                setKxian();
                ivPicByNew.setVisibility(View.VISIBLE);
                tvTimeK.setTextColor(getResources().getColor(R.color.load_red));
                break;
            case R.id.tvDayK:
                setKxian();
                ivPicByDay.setVisibility(View.VISIBLE);
                tvDayK.setTextColor(getResources().getColor(R.color.load_red));
                break;
            case R.id.tvWeekK:
                setKxian();
                tvWeekK.setTextColor(getResources().getColor(R.color.load_red));
                ivPicByWeek.setVisibility(View.VISIBLE);
                break;
            case R.id.tvMonthK:
                setKxian();
                tvMonthK.setTextColor(getResources().getColor(R.color.load_red));
                ivPicByMonth.setVisibility(View.VISIBLE);
                break;
            case R.id.tvRefreash:
                mPresenter.getGpInfoData(code, HttpClient.KAISERKEY);
                break;

        }
    }
    private void setKxian(){
        tvTimeK.setTextColor(getResources().getColor(R.color.black));
        tvDayK.setTextColor(getResources().getColor(R.color.black));
        tvWeekK.setTextColor(getResources().getColor(R.color.black));
        tvMonthK.setTextColor(getResources().getColor(R.color.black));
        ivPicByNew.setVisibility(View.GONE);
        ivPicByDay.setVisibility(View.GONE);
        ivPicByWeek.setVisibility(View.GONE);
        ivPicByMonth.setVisibility(View.GONE);
    }
}
