package com.kaiser.administrator.mymvprxjavaretrofit;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kaiser.administrator.mymvprxjavaretrofit.base.BaseActivity;
import com.kaiser.administrator.mymvprxjavaretrofit.bean.TabEntity;
import com.kaiser.administrator.mymvprxjavaretrofit.fragment.FirstTabFragment;
import com.kaiser.administrator.mymvprxjavaretrofit.fragment.SecondTabFragment;
import com.kaiser.administrator.mymvprxjavaretrofit.fragment.ThirdTabFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private FrameLayout fl_body;
    @BindView(R.id.tabLayout)
    CommonTabLayout tabLayout;

    private String[] mTitles={"自选股","深户A","页面3"};
    private int[] mIconUnselectIds={R.mipmap.n_shouye, R.mipmap.n_faxian,R.mipmap.n_geren};
    private int[] mIconSelectIds={R.mipmap.y_shouye, R.mipmap.y_faxian, R.mipmap.y_geren};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private FirstTabFragment firstTabFragment;
    private SecondTabFragment secondTabFragment;
    private ThirdTabFragment thirdTabFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
        initFragment(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        fl_body = (FrameLayout) findViewById(R.id.fl_body);
//        tabLayout = (CommonTabLayout) findViewById(R.id.tabLayout);
        initTab();
    }

    @Override
    public void setOnClicks() {

    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }
    //初始化碎片
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition=0;
        if(null==savedInstanceState){
            firstTabFragment = new FirstTabFragment();
            secondTabFragment = new SecondTabFragment();
            thirdTabFragment = new ThirdTabFragment();

            fragmentTransaction.add(R.id.fl_body,firstTabFragment,"firstTabFragment");
            fragmentTransaction.add(R.id.fl_body,secondTabFragment,"secondTabFragment");
            fragmentTransaction.add(R.id.fl_body,thirdTabFragment,"thirdTabFragment");
        }else{
            firstTabFragment= (FirstTabFragment) getSupportFragmentManager().findFragmentByTag("firstTabFragment");
            secondTabFragment= (SecondTabFragment) getSupportFragmentManager().findFragmentByTag("secondTabFragment");
            thirdTabFragment= (ThirdTabFragment) getSupportFragmentManager().findFragmentByTag("thirdTabFragment");
        }
        fragmentTransaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }
    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (position){
            case 0:
                transaction.show(firstTabFragment);
                transaction.hide(secondTabFragment);
                transaction.hide(thirdTabFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(secondTabFragment);
                transaction.hide(firstTabFragment);
                transaction.hide(thirdTabFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(thirdTabFragment);
                transaction.hide(secondTabFragment);
                transaction.hide(firstTabFragment);
                transaction.commitAllowingStateLoss();
                break;
        }
    }


}
