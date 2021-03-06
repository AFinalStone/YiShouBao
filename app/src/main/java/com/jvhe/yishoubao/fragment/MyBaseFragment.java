package com.jvhe.yishoubao.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvhe.yishoubao.activity.MyBaseActivity;

import butterknife.Unbinder;

/**
 * Created by SHI on 2016/7/27 10:37
 */
public abstract  class MyBaseFragment<T extends MyBaseActivity> extends Fragment {

    /**当前设备上下文**/
    protected T mActivity;
    /**网络请求是否成功**/
    protected boolean connectSuccessFlag = false;
    protected Unbinder unbinder;

    /**初始化界面布局**/
    public abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**初始化数据**/
    public abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mActivity == null){
            mActivity = (T)getActivity();
        }
        return initView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        connectSuccessFlag = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
        }
    }


}
