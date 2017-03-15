package com.jvhe.yishoubao.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.fragment.CheckStandFragment;
import com.jvhe.yishoubao.fragment.ResCircleFragment;
import com.jvhe.yishoubao.fragment.ShopCenterFragment;
import com.jvhe.yishoubao.fragment.ShopCenterFragmentEx;
import com.jvhe.yishoubao.fragment.ShoppingMallFragment;

public class HomeActivityEx extends MyBaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    /**
     * 店铺中心
     **/
    private ShopCenterFragmentEx mShopCenterFragment;
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

    private BottomNavigationBar bottomNavigationBar;

    @Override
    public void initView() {
        setContentView(R.layout.activity_home_ex);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        setReturnStatus(true);
    }

    @Override
    public void initData() {
        int textColorUnSelect = getResources().getColor(R.color.colorBlack_FF999999);
        int textColorSelect = getResources().getColor(R.color.colorPrimary);

        fragmentManager = getSupportFragmentManager();

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.icon_shop_center_select, R.string.MainActivity_tv_shopCenter)
                        .setInActiveColor(textColorUnSelect).setActiveColor(textColorSelect)
                        .setBadgeItem(new BadgeItem().setText("10")
                                .setBackgroundColor(Color.RED)))

                .addItem(new BottomNavigationItem(R.mipmap.icon_check_stand_select, R.string.MainActivity_tv_checkStand)
                        .setInActiveColor(textColorUnSelect).setActiveColor(textColorSelect))

                .addItem(new BottomNavigationItem(R.mipmap.icon_shopping_mall_select, R.string.MainActivity_tv_shoppingMall)
                        .setInActiveColor(textColorUnSelect).setActiveColor(textColorSelect))

                .addItem(new BottomNavigationItem(R.mipmap.icon_res_circle_select, R.string.MainActivity_tv_resCircle)
                        .setInActiveColor(textColorUnSelect).setActiveColor(textColorSelect))

                .setFirstSelectedPosition(0)
                .initialise();

        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    public void showFragmentByIndex(int position){
        bottomNavigationBar.selectTab(position);
    }
    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mShopCenterFragment = new ShopCenterFragmentEx();
        transaction.replace(R.id.frameLayout, mShopCenterFragment);
        transaction.commit();
    }

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
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (position){
            case 0:
                if (mShopCenterFragment == null) {
                    mShopCenterFragment = new ShopCenterFragmentEx();
                    transaction.add(R.id.frameLayout, mShopCenterFragment, "mShopCenterFragment");
                } else {
                    transaction.show(mShopCenterFragment);
                }
                transaction.commit();
                break;
            case 1:
                if (mCheckStandFragment == null) {
                    mCheckStandFragment = new CheckStandFragment();
                    transaction.add(R.id.frameLayout, mCheckStandFragment, "mCheckStandFragment");
                } else {
                    transaction.show(mCheckStandFragment);
                }
                transaction.commit();
                break;
            case 2:
                if (mShoppingMallFragment == null) {
                    mShoppingMallFragment = new ShoppingMallFragment();
                    transaction.add(R.id.frameLayout, mShoppingMallFragment, "mShopCenterFragment");
                } else {
                    transaction.show(mShoppingMallFragment);
                }
                transaction.commit();
                break;
            case 3:
                if (mResCircleFragment == null) {
                    mResCircleFragment = new ResCircleFragment();
                    transaction.add(R.id.frameLayout, mResCircleFragment, "mResCircleFragment");
                } else {
                    transaction.show(mResCircleFragment);
                }
                transaction.commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }



}
