package com.jvhe.yishoubao.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jvhe.yishoubao.R;
import com.jvhe.yishoubao.fragment.SaleLineFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaleStatisticsChartActivity extends MyBaseActivity {

    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    @BindView(R.id.tv_titleLeft)
    TextView tv_titleLeft;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> listFragment = new ArrayList<Fragment>();
    private MyAdapter adapter;

    private static int TAB_MARGIN_DIP = 40;
    @Override
    public void initView() {
        setContentView(R.layout.activity_sale_statistics_chart);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setText(R.string.title_back);
        tv_titleLeft.setVisibility(View.VISIBLE);
        tv_title.setText(R.string.title_SaleStatisticsChart_);

        SaleLineFragment saleLineFragment= new SaleLineFragment();
        saleLineFragment.setTitle("7天");
        listFragment.add(saleLineFragment);

        saleLineFragment= new SaleLineFragment();
        saleLineFragment.setTitle("30天");
        listFragment.add(saleLineFragment);

        saleLineFragment= new SaleLineFragment();
        saleLineFragment.setTitle("90天");

        listFragment.add(saleLineFragment);
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setIndicator(this, tabLayout, TAB_MARGIN_DIP, TAB_MARGIN_DIP);

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.linearLayout_titleLeft)
    public void onClick() {
        finish();
    }

    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }
        @Override
        public Fragment getItem(int position) {

            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  ((SaleLineFragment)listFragment.get(position)).getTitle();
        }
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    public static float getPXfromDP(float value, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }
}
