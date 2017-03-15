package com.jvhe.yishoubao.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jude.utils.JUtils;
import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.adapter.MyBaseAdapter;
import com.jvhe.yishoubao.fragment.CheckStandFragment;
import com.jvhe.yishoubao.fragment.ResCircleFragment;
import com.jvhe.yishoubao.fragment.ShopCenterFragment;
import com.jvhe.yishoubao.fragment.ShoppingMallFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends MyBaseActivity implements BottomNavigationBar.OnTabSelectedListener {

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

    private BottomNavigationBar bottomNavigationBar;

    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private List<DrawerMenuItem> listData_leftMenu;

    //侧滑菜单
    private DrawerLayout mDrawerLayout;
    private ListView listView_leftMenu;
    private AdapterListViewDrawer adapterMenuListView;
    private String[] mDrawerMenuItemTitle;
    private TypedArray mDrawerMenuItemIconArray;
    private TypedArray mDrawerMenuItemColorArray;


    @Override
    public void initView() {
        setReturnStatus(true);
        setContentView(R.layout.activity_home);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        initDrawerLayout();
    }


    private void initDrawerLayout(){
        mDrawerMenuItemTitle = getResources().getStringArray(R.array.nav_drawer_items);
        mDrawerMenuItemIconArray = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerMenuItemColorArray = getResources().obtainTypedArray(R.array.nav_drawer_tint);
        listView_leftMenu = (ListView) findViewById(R.id.listView_leftMenu);
        listData_leftMenu = new ArrayList<>();
        for (int i = 0; i < mDrawerMenuItemTitle.length; i++) {
            listData_leftMenu.add(new DrawerMenuItem(mDrawerMenuItemTitle[i]
                    , mDrawerMenuItemIconArray.getResourceId(i,-1), mDrawerMenuItemColorArray.getResourceId(i,-1)));
        }
        adapterMenuListView = new AdapterListViewDrawer(mContext,listData_leftMenu);
        listView_leftMenu.setAdapter(adapterMenuListView);
        listView_leftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
        mShopCenterFragment = new ShopCenterFragment();
        transaction.replace(R.id.frameLayout, mShopCenterFragment);
        transaction.commit();
    }


    private void hideFragments(FragmentTransaction transaction) {
        mToolbar.setVisibility(View.GONE);
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
                mToolbar.setVisibility(View.VISIBLE);
                if (mShopCenterFragment == null) {
                    mShopCenterFragment = new ShopCenterFragment();
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


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }
        super.onBackPressed();
    }


    private class DrawerMenuItem{
        private String title;
        private int icon;
        private int iconColor;

        public DrawerMenuItem(String title, int icon, int iconColor) {
            this.title = title;
            this.icon = icon;
            this.iconColor = iconColor;
        }
    }

    private class AdapterListViewDrawer extends MyBaseAdapter<DrawerMenuItem> {


        public AdapterListViewDrawer(Context mContext, List<DrawerMenuItem> listData) {
            super(mContext, listData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.adapter_main_listview_drawer_menu, null);
            }
            DrawerMenuItem item = listData.get(position);
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_icon);
            TextView txtTitle = (TextView) convertView.findViewById(R.id.drawer_title);

            imgIcon.setImageResource(item.icon);
            imgIcon.setColorFilter(item.iconColor);
            txtTitle.setText(item.title);
            return convertView;
        }
    }

}
