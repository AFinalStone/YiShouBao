package com.jvhe.yishoubao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.afinalstone.androidstudy.swiperefreshview.OnSwipeRefreshViewListener;
import com.afinalstone.androidstudy.swiperefreshview.SwipeRefreshRecycleView;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.adapter.recycler.RecyclerViewBaseAdapter;
import com.jvhe.yishoubao.model.ShoppingMallSelectCondition;
import com.jvhe.yishoubao.util.SnackBarUtil;
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
public class ShoppingMallFragment extends MyBaseFragment<AppCompatActivity> implements OnSwipeRefreshViewListener{

    @BindView(R.id.linearLayout_title)
    LinearLayout linearLayout_title;

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
        popWindow = new PopWindowSelectCondition(mActivity, listData_ShoppingMallSelectCondition,new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cb_selectShopCondition.setText(listData_ShoppingMallSelectCondition.get(position).modelName);
            }
        });

        cb_selectShopCondition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    popWindow.showAsDropDown(linearLayout_title);
                }
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cb_selectShopCondition.setChecked(false);
            }
        });
        listData_Model.add(new Model());
        listData_Model.add(new Model());
        listData_Model.add(new Model());
        listData_Model.add(new Model());
        listData_Model.add(new Model());
        listData_Model.add(new Model());
        myRecycleAdapter = new MyRecycleAdapter(mActivity,listData_Model);
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
                ToastUtil.show(mActivity,"刷新数据");
                swipeRefreshRecycleView.closeRefreshState();
            }
        },3000);
    }

    @Override
    public void onBottomRefrushListener() {
        swipeRefreshRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show(mActivity,"底部刷新");
                swipeRefreshRecycleView.closeRefreshState();
            }
        },3000);
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

        }


        protected class MyViewHolder extends RecyclerView.ViewHolder {
            public MyViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
    private class Model{

    }

}
