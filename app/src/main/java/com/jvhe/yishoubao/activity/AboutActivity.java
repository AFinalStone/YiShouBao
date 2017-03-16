package com.jvhe.yishoubao.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jvhe.yishoubao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：Anumbrella
 * Date：16/6/25 下午10:44
 */
public class AboutActivity extends MyBaseActivity {


    @BindView(R.id.iv_titleLeft)
    ImageView iv_titleLeft;

    @BindView(R.id.tv_titleLeft)
    TextView tv_titleLeft;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    public void initView() {
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        iv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setVisibility(View.VISIBLE);
        tv_titleLeft.setText(R.string.title_back);
        tv_title.setText(R.string.title_About);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.linearLayout_titleLeft)
    public void onClick() {
        finish();
    }
}
