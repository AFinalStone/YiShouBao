package com.jvhe.yishoubao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshRecycleView;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.activity.MyBaseActivity;
import com.jvhe.yishoubao.adapter.recycler.RecyclerViewBaseAdapter;
import com.jvhe.yishoubao.model.ShoppingMallSelectCondition;
import com.jvhe.yishoubao.util.ToastUtil;
import com.jvhe.yishoubao.view.PopWindowSelectCondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by SHI on 2017/3/1 13:58
 * 商城
 */
public class ShoppingMallFragment extends MyBaseFragment<MyBaseActivity> implements OnSwipeRefreshViewListener {

    @BindView(R.id.view_border)
    View view_border;

    @BindView(R.id.cb_selectShopCondition)
    CheckBox cb_selectShopCondition;

    private PopWindowSelectCondition popWindow;

    @BindView(R.id.swipeRefreshRecycleView)
    SwipeRefreshRecycleView swipeRefreshRecycleView;

    private MyRecycleAdapter myRecycleAdapter;

    private List<ShoppingMallSelectCondition> listData_ShoppingMallSelectCondition = new ArrayList<>();
    private List<Model> listData_Model = new ArrayList<>();

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(mActivity, R.layout.fragment_shopping_mall, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_dition_safety
                , mActivity.getString(R.string.ShoppingMall_conditionDitionSafety)));


        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_office_furniture
                , mActivity.getString(R.string.ShoppingMall_conditionOfficeFurniture)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_office_equipment
                , mActivity.getString(R.string.ShoppingMall_conditionOfficeEquipment)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_office_supplies
                , mActivity.getString(R.string.ShoppingMall_conditionOfficeSupplies)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_lanterns_goods
                , mActivity.getString(R.string.ShoppingMall_conditionLanternsGoods)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_elec_device
                , mActivity.getString(R.string.ShoppingMall_conditionElecDevice)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_teaching_research
                , mActivity.getString(R.string.ShoppingMall_conditionTeachingResearch)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_labour_protection_appliances
                , mActivity.getString(R.string.ShoppingMall_conditionLabourProtectionAppliances)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_general_merchandise
                , mActivity.getString(R.string.ShoppingMall_conditionGeneralMerchandise)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_life_elec
                , mActivity.getString(R.string.ShoppingMall_conditionLifeElec)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_food_beverage
                , mActivity.getString(R.string.ShoppingMall_conditionFoodBeverage)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_digital_device
                , mActivity.getString(R.string.ShoppingMall_conditionDigitalDevice)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_sports_goods
                , mActivity.getString(R.string.ShoppingMall_conditionSportsGoods)));

        listData_ShoppingMallSelectCondition.add(new ShoppingMallSelectCondition(R.mipmap.icon_shopping_mall_condition_hardware_tools
                , mActivity.getString(R.string.ShoppingMall_conditionHardwareTools)));
        popWindow = new PopWindowSelectCondition(mActivity, listData_ShoppingMallSelectCondition, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cb_selectShopCondition.setText(listData_ShoppingMallSelectCondition.get(position).modelName);
            }
        });

        cb_selectShopCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    popWindow.showAsDropDown(linearLayout_title);
                    popWindow.showAsDropDown(view_border);
                }
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cb_selectShopCondition.setChecked(false);
            }
        });
        listData_Model.add(new Model("杭州乡里乡亲", "com.xianglixiangqin.xianglixiangqin", R.mipmap.logo_xianglixiangqin, "www.xianglixiangqin.com"));

        listData_Model.add(new Model("杭州全能批发王", "com.quannengpifawang.xianglixiangqin", R.mipmap.logo_quannengpifawang, "www.quannengpifawang.com"));

        listData_Model.add(new Model("水木年华", "com.shuimunianhua.xianglixiangqin", R.mipmap.logo_shuimunianhua, "www.shuimunianhua.com"));
        listData_Model.add(new Model("锋调宇顺", "com.fengtiaoyushun.xianglixiangqin", R.mipmap.logo_fengtiaoyushun, "www.fengtiaoyushun.com"));
        listData_Model.add(new Model("杭州乡里乡亲", "com.xianglixiangqin.xianglixiangqin", R.mipmap.logo_xianglixiangqin, "www.xianglixiangqin.com"));
        listData_Model.add(new Model("杭州乡里乡亲", "com.xianglixiangqin.xianglixiangqin", R.mipmap.logo_xianglixiangqin, "www.xianglixiangqin.com"));
        myRecycleAdapter = new MyRecycleAdapter(mActivity, listData_Model);
        swipeRefreshRecycleView.setOnRefreshListener(this);
        swipeRefreshRecycleView.getItemView().setLayoutManager(new LinearLayoutManager(mActivity));
        swipeRefreshRecycleView.getItemView().setAdapter(myRecycleAdapter);
        swipeRefreshRecycleView.IfOpenBottomRefresh(true);
    }

    @OnClick({R.id.cb_selectShopCondition, R.id.tv_applyEnter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_applyEnter:

                break;
        }
    }

    @Override
    public void onTopRefrushListener() {
        swipeRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show(mActivity, "刷新数据");
                swipeRefreshRecycleView.closeRefreshState();
            }
        }, 3000);
    }

    @Override
    public void onBottomRefrushListener() {
        swipeRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show(mActivity, "底部刷新");
                swipeRefreshRecycleView.closeRefreshState();
            }
        }, 3000);
    }

    /**
     * 适配器
     **/
    protected class MyRecycleAdapter extends RecyclerViewBaseAdapter<MyRecycleAdapter.MyViewHolder, Model> {

        public MyRecycleAdapter(Context context, List<Model> listData) {
            super(context, listData);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mLayoutInflater.inflate(R.layout.adapter_shopping_mall_recycler_view_item, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            final Model model = listData.get(position);
            holder.iv_shopLogo.setImageResource(model.icon_ResId);
            holder.tv_shopName.setText(model.shopName);
            holder.tv_shopUrl.setText(model.shopUrl);
            holder.iv_shopImage.setImageResource(model.icon_ResId);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openOtherApp(model.appPackageName);
                }
            });
        }


        protected class MyViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.iv_shopLogo)
            ImageView iv_shopLogo;
            @BindView(R.id.btn_applyAgent)
            Button btn_applyAgent;
            @BindView(R.id.btn_downApp)
            Button btn_downApp;
            @BindView(R.id.tv_shopName)
            TextView tv_shopName;
            @BindView(R.id.tv_shopUrl)
            TextView tv_shopUrl;
            @BindView(R.id.iv_shopImage)
            ImageView iv_shopImage;
            @BindView(R.id.tv_ShopDesc)
            TextView tv_shopDesc;

            MyViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    private class Model {
        private String shopName;
        private String appPackageName;
        private String shopUrl;
        private int icon_ResId;

        public Model(String shopName, String appPackageName, int icon_ResId, String shopUrl) {
            this.shopName = shopName;
            this.appPackageName = appPackageName;
            this.icon_ResId = icon_ResId;
            this.shopUrl = shopUrl;
        }
    }


    private void openOtherApp(String packageName) {
        // 通过包名获取要跳转的app，创建intent对象
        Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(packageName);
        // 这里如果intent为空，就说名没有安装要跳转的应用嘛
        if (intent != null) {
            // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
            startActivity(intent);
        } else {
            // 没有安装要跳转的app应用，提醒一下
            ToastUtil.show(mActivity, "哟，赶紧下载安装这个APP吧");
        }
    }
}
