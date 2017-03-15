package com.jvhe.yishoubao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshListView;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.adapter.MyBaseAdapter;
import com.jvhe.yishoubao.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  SHI on 2017/3/1 13:58
 * 资源圈
 */
public class ResCircleFragment extends MyBaseFragment<AppCompatActivity> {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.swipeRefreshListView)
    SwipeRefreshListView swipeRefreshListView;

    private List<ShopRecommendModel> listData = new ArrayList<ShopRecommendModel>();
    private MyShopRecommendAdapter myShopRecommendAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = View.inflate(mActivity, R.layout.fragment_res_circle, null);
        ButterKnife.bind(this, rootView);
        tv_title.setText(R.string.title_ResCircle);
        return rootView;
    }

    @Override
    public void initData() {
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        listData.add(new ShopRecommendModel());
        myShopRecommendAdapter = new MyShopRecommendAdapter(mActivity, listData);
        swipeRefreshListView.getListView().setAdapter(myShopRecommendAdapter);
        swipeRefreshListView.setOnRefreshListener(new OnSwipeRefreshViewListener() {
            @Override
            public void onTopRefrushListener() {
                swipeRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show(mActivity, "顶部刷新");
                        swipeRefreshListView.closeRefreshState();
                    }
                }, 3000);
            }

            @Override
            public void onBottomRefrushListener() {
                swipeRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.show(mActivity, "底部刷新");
                        swipeRefreshListView.closeRefreshState();
                    }
                }, 3000);
            }
        });
    }

    private class MyShopRecommendAdapter extends MyBaseAdapter<ShopRecommendModel> {

        public MyShopRecommendAdapter(Context mContext, List<ShopRecommendModel> listData) {
            super(mContext, listData);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.adapter_res_circle_listview_parent, null);
                viewHolder.tv_shopName = (TextView) convertView.findViewById(R.id.tv_shopName);
                viewHolder.btn_IntoShop = (Button) convertView.findViewById(R.id.btn_IntoShop);
                viewHolder.tv_shopDesc = (TextView) convertView.findViewById(R.id.tv_shopDesc);
                viewHolder.linearLayout_specialPricesGoods = (LinearLayout) convertView.findViewById(R.id.linearLayout_specialPricesGoods);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.linearLayout_specialPricesGoods.removeAllViews();
            }

            return convertView;
        }

        private class ViewHolder {
            public TextView tv_shopName;
            public Button btn_IntoShop;
            public TextView tv_shopDesc;
            public LinearLayout linearLayout_specialPricesGoods;
        }
    }

    private class ShopRecommendModel {
        /**
         * 标识
         **/
        public int ID;
        /**
         * 店铺名称
         **/
        public String ShopName;
        /**
         * 店铺简介
         **/
        public String Remark;
    }

}
