package com.jvhe.yishoubao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.activity.AddressManageActivity;
import com.jvhe.yishoubao.activity.HomeActivity;
import com.jvhe.yishoubao.activity.MyShopActivity;
import com.jvhe.yishoubao.activity.SaleStatisticsChartActivity;
import com.jvhe.yishoubao.adapter.MyBaseAdapter;
import com.jvhe.yishoubao.util.ImagerLoaderUtil;
import com.jvhe.yishoubao.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SHI on 2017/3/1 13:58
 * 店铺
 */
public class ShopCenterFragment extends MyBaseFragment<HomeActivity> implements AdapterView.OnItemClickListener {

    @BindView(R.id.tv_sevenDaysSales)
    TextView tv_sevenDaysSales;

    @BindView(R.id.gridView)
    GridView gridView;

//    @BindView(R.id.iv_titleRight)
//    ImageView iv_titleRight;
//
//    @BindView(R.id.tv_title)
//    TextView tv_title;

    List<Model> listData;
    MyBaseAdapter myBaseAdapter;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = View.inflate(mActivity, R.layout.fragment_shop_center, null);
        ButterKnife.bind(this, rootView);
//        iv_titleRight.setImageResource(R.mipmap.icon_shop_center_setting);
//        iv_titleRight.setVisibility(View.VISIBLE);
//        tv_title.setText(R.string.title_shopCenter);
        return rootView;

    }

    @Override
    public void initData() {
        listData = new ArrayList<>();
        listData.add(new Model(R.mipmap.icon_shop_center_model_check_stand, getString(R.string.ShopCenter_tv_checkStand)));
        listData.add(new Model(R.mipmap.icon_shop_center_model_goods_manager, getString(R.string.ShopCenter_tv_goodsManager)));
        listData.add(new Model(R.mipmap.icon_shop_center_model_address_manager, getString(R.string.ShopCenter_tv_addressManager)));
        listData.add(new Model(R.mipmap.icon_shop_center_model_my_shop, getString(R.string.ShopCenter_tv_myShop)));
        listData.add(new Model(R.mipmap.icon_shop_center_model_wx_shop_manager, getString(R.string.ShopCenter_tv_wxShopManager)));
        listData.add(new Model(R.mipmap.icon_shop_center_model_account_manager, getString(R.string.ShopCenter_tv_accountManager)));
        myBaseAdapter = new MyGridAdapter(mActivity, listData);

        gridView.setAdapter(myBaseAdapter);
        gridView.setOnItemClickListener(this);
    }


    @OnClick({R.id.linearLayout_sevenDaysSales})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_titleRight:

                break;
            case R.id.linearLayout_titleRight:

                break;
            case R.id.linearLayout_sevenDaysSales:
                startActivity(new Intent(mActivity, SaleStatisticsChartActivity.class));
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String modelName = listData.get(position).modelName;
        ToastUtil.show(mActivity, modelName);
        if(getString(R.string.ShopCenter_tv_checkStand).equals(modelName)){
            mActivity.showFragmentByIndex(1);
            return;
        }
        if(getString(R.string.ShopCenter_tv_goodsManager).equals(modelName)){
            return;
        }
         if(getString(R.string.ShopCenter_tv_addressManager).equals(modelName)){
             startActivity(new Intent(mActivity, AddressManageActivity.class));
             return;
        }
         if(getString(R.string.ShopCenter_tv_myShop).equals(modelName)){
             startActivity(new Intent(mActivity, MyShopActivity.class));
            return;
        }
         if(getString(R.string.ShopCenter_tv_checkStand).equals(modelName)){
            return;
        }
         if(getString(R.string.ShopCenter_tv_wxShopManager).equals(modelName)){
            return;
        }
         if(getString(R.string.ShopCenter_tv_accountManager).equals(modelName)){
            return;
        }
    }

    protected class MyGridAdapter extends MyBaseAdapter<Model> {

        public MyGridAdapter(Context mContext, List<Model> listData) {
            super(mContext, listData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder = null;
            if (convertView == null) {
                holder = new MyViewHolder();
                convertView = View.inflate(mActivity, R.layout.adapter_shop_center_gridview_item, null);
                holder.iv_model = (ImageView) convertView.findViewById(R.id.iv_model);
                holder.tv_modelName = (TextView) convertView.findViewById(R.id.tv_modelName);
                convertView.setTag(holder);
            } else {
                holder = (MyViewHolder) convertView.getTag();
            }
            Model model = listData.get(position);
            ImagerLoaderUtil.getInstance(mActivity).displayMyImage(model.modelUrl, holder.iv_model);
            holder.tv_modelName.setText(model.modelName);

            return convertView;
        }

        protected class MyViewHolder {
            private ImageView iv_model;
            private TextView tv_modelName;
        }
    }

    private class Model {
        protected int modelUrl;
        protected String modelName;

        public Model(int modelUrl, String modelName) {
            this.modelUrl = modelUrl;
            this.modelName = modelName;
        }
    }
}
