package com.kaiser.administrator.mymvprxjavaretrofit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiser.administrator.mymvprxjavaretrofit.R;

/**
 * Created by Administrator on 2017/8/11.
 */
public class ThirdTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third,container,false);
    }
}
