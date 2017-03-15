package com.jvhe.yishoubao.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.fragment.CheckStandFragment;
import com.jvhe.yishoubao.fragment.ResCircleFragment;
import com.jvhe.yishoubao.fragment.ShopCenterFragment;
import com.jvhe.yishoubao.fragment.ShoppingMallFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页
 *
 * @author SHI
 * @time 2017/3/1 13:58
 */
public class MainActivity extends MyBaseActivity {

    //店铺中心
    @BindView(R.id.iv_shopCenter)
    ImageView iv_shopCenter;
    @BindView(R.id.tv_shopCenter)
    TextView tv_shopCenter;
    @BindView(R.id.linearLayout_shopCenter)
    LinearLayout linearLayout_shopCenter;

    //收银台
    @BindView(R.id.iv_checkStand)
    ImageView iv_checkStand;
    @BindView(R.id.tv_checkStand)
    TextView tv_checkStand;
    @BindView(R.id.linearLayout_checkStand)
    LinearLayout linearLayout_checkStand;

    //商城
    @BindView(R.id.iv_shoppingMall)
    ImageView iv_shoppingMall;
    @BindView(R.id.tv_shoppingMall)
    TextView tv_shoppingMall;
    @BindView(R.id.linearLayout_shoppingMall)
    LinearLayout linearLayout_shoppingMall;

    //资源圈
    @BindView(R.id.iv_resCircle)
    ImageView iv_resCircle;
    @BindView(R.id.tv_resCircle)
    TextView tv_resCircle;
    @BindView(R.id.linearLayout_resCircle)
    LinearLayout linearLayout_resCircle;
    /**
     * 店铺中心
     **/
    private ShopCenterFragment mShopCenterFragment;
    /**
     * 收银台
     **/
    private CheckStandFragment mCheckStandFragment;
    /**
     * 商城中心
     **/
    private ShoppingMallFragment mShoppingMallFragment;
    /**
     * 资源圈
     **/
    private ResCircleFragment mResCircleFragment;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    //字体未选中颜色
    private int textColorUnSelect;
    //字体选中颜色
    private int textColorSelect;
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        fragmentManager = getSupportFragmentManager();
        textColorUnSelect = getResources().getColor(R.color.colorBlack_FF999999);
        textColorSelect = getResources().getColor(R.color.colorPrimary);
        showViewByViewId(R.id.linearLayout_shopCenter);
    }

    /**
     * 根据ID切换展示页面
     **/
    public void showViewByViewId(int viewId) {
        switch (viewId) {
            case R.id.linearLayout_shopCenter:
                linearLayout_shopCenter.callOnClick();
                break;
            case R.id.linearLayout_checkStand:
                linearLayout_checkStand.callOnClick();
                break;
            case R.id.linearLayout_shoppingMall:
                linearLayout_shoppingMall.callOnClick();
                break;
            case R.id.linearLayout_resCircle:
                linearLayout_resCircle.callOnClick();
                break;
            default:
                break;
        }
    }


    private void showShopCenterFragment() {

        iv_checkStand.setImageResource(R.mipmap.icon_check_stand_unselect);
        tv_checkStand.setTextColor(textColorUnSelect);

        iv_shoppingMall.setImageResource(R.mipmap.icon_shopping_mall_unselect);
        tv_shoppingMall.setTextColor(textColorUnSelect);

        iv_resCircle.setImageResource(R.mipmap.icon_res_circle_unselect);
        tv_resCircle.setTextColor(textColorUnSelect);

        iv_shopCenter.setImageResource(R.mipmap.icon_shop_center_select);
        tv_shopCenter.setTextColor(textColorSelect);

        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (mShopCenterFragment == null) {
            mShopCenterFragment = new ShopCenterFragment();
            transaction.add(R.id.frameLayout, mShopCenterFragment, "mShopCenterFragment");
        } else {
            transaction.show(mShopCenterFragment);
        }
        transaction.commit();
    }

    private void showCheckStandFragment() {

        iv_shopCenter.setImageResource(R.mipmap.icon_shop_center_unselect);
        tv_shopCenter.setTextColor(textColorUnSelect);

        iv_shoppingMall.setImageResource(R.mipmap.icon_shopping_mall_unselect);
        tv_shoppingMall.setTextColor(textColorUnSelect);

        iv_resCircle.setImageResource(R.mipmap.icon_res_circle_unselect);
        tv_resCircle.setTextColor(textColorUnSelect);

        iv_checkStand.setImageResource(R.mipmap.icon_check_stand_select);
        tv_checkStand.setTextColor(textColorSelect);

        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (mCheckStandFragment == null) {
            mCheckStandFragment = new CheckStandFragment();
            transaction.add(R.id.frameLayout, mCheckStandFragment, "mCheckStandFragment");
        } else {
            transaction.show(mCheckStandFragment);
        }
        transaction.commit();
    }

    private void showShoppingMallFragment() {
        iv_shoppingMall.setImageResource(R.mipmap.icon_shopping_mall_select);
        tv_shoppingMall.setTextColor(textColorSelect);

        iv_shopCenter.setImageResource(R.mipmap.icon_shop_center_unselect);
        tv_shopCenter.setTextColor(textColorUnSelect);

        iv_checkStand.setImageResource(R.mipmap.icon_check_stand_unselect);
        tv_checkStand.setTextColor(textColorUnSelect);

        iv_resCircle.setImageResource(R.mipmap.icon_res_circle_unselect);
        tv_resCircle.setTextColor(textColorUnSelect);

        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (mShoppingMallFragment == null) {
            mShoppingMallFragment = new ShoppingMallFragment();
            transaction.add(R.id.frameLayout, mShoppingMallFragment, "mShopCenterFragment");
        } else {
            transaction.show(mShoppingMallFragment);
        }
        transaction.commit();
    }

    private void showResCircleFragment() {
        iv_resCircle.setImageResource(R.mipmap.icon_res_circle_select);
        tv_resCircle.setTextColor(textColorSelect);

        iv_shopCenter.setImageResource(R.mipmap.icon_shop_center_unselect);
        tv_shopCenter.setTextColor(textColorUnSelect);

        iv_checkStand.setImageResource(R.mipmap.icon_check_stand_unselect);
        tv_checkStand.setTextColor(textColorUnSelect);

        iv_shoppingMall.setImageResource(R.mipmap.icon_shopping_mall_unselect);
        tv_shoppingMall.setTextColor(textColorUnSelect);

        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        if (mResCircleFragment == null) {
            mResCircleFragment = new ResCircleFragment();
            transaction.add(R.id.frameLayout, mResCircleFragment, "mResCircleFragment");
        } else {
            transaction.show(mResCircleFragment);
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (mShopCenterFragment != null) {
            transaction.hide(mShopCenterFragment);
        }
        if (mCheckStandFragment != null) {
            transaction.hide(mCheckStandFragment);
        }
        if (mShoppingMallFragment != null) {
            transaction.hide(mShoppingMallFragment);
        }
        if (mResCircleFragment != null) {
            transaction.hide(mResCircleFragment);
        }
        transaction.commit();
    }

    @OnClick({R.id.linearLayout_shopCenter, R.id.linearLayout_checkStand, R.id.linearLayout_shoppingMall, R.id.linearLayout_resCircle})
    public void onClick(View view) {

        hideFragments(fragmentManager.beginTransaction());

        switch (view.getId()) {
            case R.id.linearLayout_shopCenter:
                showShopCenterFragment();
                break;

            case R.id.linearLayout_checkStand:
                showCheckStandFragment();
                break;

            case R.id.linearLayout_shoppingMall:
                showShoppingMallFragment();
                break;

            case R.id.linearLayout_resCircle:
                showResCircleFragment();
                break;
        }
    }
}
