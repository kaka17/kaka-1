package com.kaiser.kkshares.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kaiser.kkshares.R;
import com.kaiser.kkshares.activity.KaiserGuInfoActivity;
import com.kaiser.kkshares.adapter.SecondAdapter;
import com.kaiser.kkshares.api.HttpClient;
import com.kaiser.kkshares.base.BaseFragment;
import com.kaiser.kkshares.base.baseadapter.BaseQuickAdapter;
import com.kaiser.kkshares.base.baseadapter.OnItemClickListener;
import com.kaiser.kkshares.bean.SeconData;
import com.kaiser.kkshares.bean.SecondBean;
import com.kaiser.kkshares.bean.ThirdData;
import com.kaiser.kkshares.bean.User;
import com.kaiser.kkshares.configs.Config;
import com.kaiser.kkshares.contract.SecondContract;
//import com.kaiser.administrator.mymvprxjavaretrofit.db.DBManager;
import com.kaiser.kkshares.db.DBManager;
import com.kaiser.kkshares.model.SecondModel;
import com.kaiser.kkshares.myinterface.MyItemClickListener;
import com.kaiser.kkshares.presenter.SecondPresenter;
import com.kaiser.kkshares.utils.ListUitls;
import com.kaiser.kkshares.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/8/11.
 */
public class SecondTabFragment extends  BaseFragment<SecondPresenter,SecondModel> implements SecondContract.SecondView, BaseQuickAdapter.RequestLoadMoreListener,View.OnClickListener,MyItemClickListener {
/*
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
return inflater.inflate(R.layout.fragment_second, container, false);
}
*/
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.tvResf)
    TextView tvResf;
     @BindView(R.id.tvDeal)
    TextView tvDeal;
     @BindView(R.id.tvChang)
    TextView tvChang;
    @BindView(R.id.tvAdd)
    TextView tvAdd;
 @BindView(R.id.tvTop)
    TextView tvTop;

    private SecondAdapter Sadapter;
    private Context mContext;
    private int num=1;
    private  List<ThirdData> list=new ArrayList<>();
    private  LinearLayoutManager manager;
    private DBManager dbManager;
    @Override
    protected int getLayoutResource() {
        mContext=getActivity();
        return R.layout.fragment_second;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
//        tvName.setText("这是第二个页面");
        dbManager=DBManager.getInstance(mContext);
        Sadapter=new SecondAdapter(R.layout.second_item,null);
        Sadapter.setOnClickListener(this);
        manager = new LinearLayoutManager(mContext);
        rvList.setLayoutManager(manager);
        rvList.setHasFixedSize(true);
        //设置适配器加载动画
        Sadapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        Sadapter.isFirstOnly(false);
        rvList.setAdapter(Sadapter);
        //设置适配器可以上拉加载
        Sadapter.setOnLoadMoreListener(this);
//        //设置下拉、上拉
//        refreshLayout.setDelegate(this);
//        refreshLayout.setRefreshViewHolder(new NormalRefreshViewHolder(mContext, true));

        mPresenter.getLockList(HttpClient.KAISERKEY, "4", num+"",true);

    }

    @Override
    protected void setMyOnClickListener() {
        tvResf.setOnClickListener(this);
        tvDeal.setOnClickListener(this);
        tvChang.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvTop.setOnClickListener(this);

        rvList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
//                showLongToast(position + "");
                Bundle bundle=new Bundle();
                bundle.putString(Config.GPCODE,((ThirdData)Sadapter.getData().get(position)).getSymbol());//symbol
                startActivity(KaiserGuInfoActivity.class, bundle);
            }
        });

    }

    @Override
    public void showData(List<SecondBean> list) {
        Log.e("TAAG","-------------------->"+list.toString());
    }

    @Override
    public void showData(SeconData seconData) {

    }

    @Override
    public void showDatas(SeconData conData,boolean isRefresh) {
//        Log.e("TAAG","-------------------->"+conData.toString());
//        List<ThirdData> thirdDatas = conData.getResult().getData();
        Log.e("TAAG", "-------------------->" + conData.getReason());
//        adapter.addData(conData.getResult().getData());
        if (isRefresh){
            Sadapter.getData().clear();
            Sadapter.setNewData(conData.getResult().getData());
        }else {
            Sadapter.addData(conData.getResult().getData());
        }
    }

    @Override
    public void showLoading(String title) {
        Log.e("TAAG", "-------------------->"+title);
        LoadingDialog.showDialogForLoading(getActivity());
    }

    @Override
    public void stopLoading() {
        LoadingDialog.cancelDialogForLoading();

        Log.e("TAAG", "---------------stopLoading----->" );
    }

    @Override
    public void showErrorTip(String msg) {
        showLongToast(msg);
        Log.e("TAAG", "--------------------showErrorTip>"+msg);
    }

    @Override
    public void onLoadMoreRequested() {
        num++;
        Log.e("TAAG", "--------------------onLoadMoreRequested>");
        mPresenter.getLockList(HttpClient.KAISERKEY,  "1",num+"",false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvResf:
                num=1;
                 mPresenter.getLockList(HttpClient.KAISERKEY,  "4",1+"",true);
                break;
             case R.id.tvChang:
                 ListUitls.sortList(Sadapter.getData(),"changepercent","DESC");
                 Sadapter.notifyDataSetChanged();
                 break;
             case R.id.tvDeal:
                 ListUitls.sortList(Sadapter.getData(),"volume","DESC");
                 Sadapter.notifyDataSetChanged();
                break;
                case R.id.tvAdd:
                    num++;
                    tvAdd.setText("加1页"+num);
                    mPresenter.getLockList(HttpClient.KAISERKEY, "4", num + "", false);
//                    ListUitls.sortList(adapter.getData(),"volume","DESC");
//                 adapter.notifyDataSetChanged();
                break;
            case R.id.tvTop:
//                rvList.scrollTo(0,0);
                manager.scrollToPositionWithOffset(0, 0);
                manager.setStackFromEnd(true);
                break;

        }

    }

    @Override
    public void onItemClick(View view, int postion) {
        List<ThirdData> data = Sadapter.getData();
        dbManager.insertUser(new User(11,data.get(postion).toString(),data.get(postion).getVolume(),data.get(postion).getCode()));
    }
}
